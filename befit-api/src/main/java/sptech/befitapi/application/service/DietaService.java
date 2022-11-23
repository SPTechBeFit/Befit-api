package sptech.befitapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import sptech.befitapi.application.Util.FilaObj;
import sptech.befitapi.application.request.DietaRequest;
import sptech.befitapi.application.response.CatalogoDietaResponse;
import sptech.befitapi.application.response.DietaCompleta;
import sptech.befitapi.application.response.DietaFavoritaResponse;
import sptech.befitapi.resources.repository.*;
import sptech.befitapi.resources.repository.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@EnableScheduling
@Service
public class DietaService {

    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private IngredientesDietaRepository ingredientesDietaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DietaFavoritaRepository dietaFavoritaRepository;

    @Autowired
    private ImagensPexelService imagensPexelService;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public Dieta cadastrar(@RequestBody DietaRequest dieta) {
        Usuario usuario = usuarioRepository.findByPersonId(dieta.getPersonId());

        if (usuario == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível encontrar o usuário"
            );
        }

        dieta.getDieta().setCriador(usuario);

        Dieta dietaDB = dietaRepository.save(dieta.getDieta());

        for (IngredientesDieta ingrediente: dieta.getIngredientesDieta()) {
            ingrediente.setDieta(dietaDB);
            ingredientesDietaRepository.save(ingrediente);
        }

        return dietaDB;
    }

    public List<CatalogoDietaResponse> getCatalogo(String personId) {
        List<Dieta> dietas = dietaRepository.findAll();

        if (dietas.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível encontrar dietas"
            );
        }

        Usuario usuario = usuarioRepository.findByPersonId(personId);

        if (usuario == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível encontrar o usuário"
            );
        }

        List<CatalogoDietaResponse> catalogo = new ArrayList<>();

        List<DietaFavorita> dietaByUsuarioId = dietaFavoritaRepository.findDietaByUsuarioId(usuario.getId());

        for (Dieta d : dietas) {
            boolean achou = false;
            for (DietaFavorita df : dietaByUsuarioId) {
                if (d.getId().equals(df.getDieta().getId())) {
                    catalogo.add(new CatalogoDietaResponse(
                            d.getId(),
                            d.getNome(),
                            d.getDescricao(),
                            d.getImagem(),
                            true
                    ));
                    achou = true;
                }
            }
            if (!achou) {
                catalogo.add(new CatalogoDietaResponse(
                        d.getId(),
                        d.getNome(),
                        d.getDescricao(),
                        d.getImagem(),
                        false
                ));
            }

        }
        return catalogo;
    }

    public DietaCompleta getById(int id) {
        Optional<Dieta> dieta = dietaRepository.findById(id);

        if (dieta.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível encontrar a dieta"
            );
        }

        List<IngredientesDieta> ingredientesDietas = ingredientesDietaRepository.findIngredientesDietaByDietaId(id);

        if (ingredientesDietas.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível encontrar os ingredientes da dieta"
            );
        }

        List<Ingrediente> ingredientes = new ArrayList<>();

        for (IngredientesDieta i : ingredientesDietas) {
            i.getIngrediente().setPorcao((int) (i.getIngrediente().getPorcao() * i.getQuantidade()));
            i.getIngrediente().setProteina(i.getIngrediente().getProteina() * i.getQuantidade());
            i.getIngrediente().setLipidio(i.getIngrediente().getLipidio() * i.getQuantidade());
            i.getIngrediente().setCarboidrato(i.getIngrediente().getCarboidrato() * i.getQuantidade());
            i.getIngrediente().setSodio(i.getIngrediente().getSodio() * i.getQuantidade());
            i.getIngrediente().setCaloria(i.getIngrediente().getCaloria() * i.getQuantidade());
            ingredientes.add(i.getIngrediente());
        }



        return new DietaCompleta(dieta.get(), ingredientes);
    }

    public Boolean favoritar(String personId, int dietaId) {
        Usuario usuario = usuarioRepository.findByPersonId(personId);

        if (usuario == null) {
            return false;
        }

        boolean dietaExiste = dietaRepository.existsById(dietaId);

        if (!dietaExiste) {
            return false;
        }

        dietaFavoritaRepository.save(new DietaFavorita(new Usuario(usuario.getId()), new Dieta(dietaId)));
        return true;

    }

    public Boolean deleteFavorito(String personId, int dietaId) {
        Usuario usuario = usuarioRepository.findByPersonId(personId);

        if (usuario == null) {
            return false;
        }

        DietaFavorita dietaFavorita = dietaFavoritaRepository.findDietaFavoritaByUsuarioIdAndDietaId(usuario.getId(), dietaId);
        if (dietaFavorita == null) {
            return false;
        }


        dietaFavoritaRepository.deleteDietaFavoritaById(dietaFavorita.getId());
        return true;

    }

    public List<DietaFavoritaResponse> getFavoritos(String personId) {

        Usuario usuario = usuarioRepository.findByPersonId(personId);

        if (usuario == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível encontrar o usuário"
            );
        }

        List<DietaFavorita> dietas = dietaFavoritaRepository.findDietaByUsuarioId(usuario.getId());
        if (dietas.isEmpty()) {
            return null;
        }
        return new DietaFavoritaResponse().fromDietaFavoritaRepository(dietas);
    }

    FilaObj<byte[]> filaObj = new FilaObj<>(20);
    FilaObj<String> filaPersonId = new FilaObj<>(20);

    private String cabecalhoDietaTxt(){
        String corpo;
        corpo = "   ";
        corpo += String.format("%-15.15s ", "Nome");
        corpo += String.format("%-40.40s", "Descricao");
        corpo += String.format("%-20.20s", "Ingrediente");
        corpo += String.format("%7.7s", "Porcao");
        corpo += String.format("%9.9s", "Proteina");
        corpo += String.format("%8.8s", "Lipidio");
        corpo += String.format("%12.12s", "Carboidrato");
        corpo += String.format("%6.6s", "Sodio");
        corpo += String.format("%8.8s", "Caloria");
        return corpo;
    }

    public byte[] gravarDietaTxt(Integer idDieta) {

        DietaCompleta dietaCompleta = this.getById(idDieta);
        if (dietaCompleta.getIngredientes().size() == 0){
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "Não foi possível encontrar os ingredientes");
        }

        int contaRegDados = 0;
        String arquivo = null;

        String header = "00DIETA";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        arquivo = header;

        arquivo += "\n"+cabecalhoDietaTxt()+"";

        Dieta dieta = dietaCompleta.getDieta();
        List<Ingrediente> ingredientes = dietaCompleta.getIngredientes();


        String corpo;
        for (int i = 0; i < ingredientes.size(); i++) {

            corpo = "02 ";
            corpo += String.format("%-15.15s ", dieta.getNome());
            corpo += String.format("%-40.40s",  dieta.getDescricao());
            corpo += String.format("%-20.20s", ingredientes.get(i).getNome());
            corpo += String.format("%7d", ingredientes.get(i).getPorcao());
            corpo += String.format("%9.2f", ingredientes.get(i).getProteina());
            corpo += String.format("%8.2f", ingredientes.get(i).getLipidio());
            corpo += String.format("%12.2f", ingredientes.get(i).getCarboidrato());
            corpo += String.format("%6.2f", ingredientes.get(i).getSodio());
            corpo += String.format("%8.2f", ingredientes.get(i).getCaloria());

            arquivo += "\n"+corpo+"";
            contaRegDados++;

        }
        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);
        arquivo += "\n"+trailer+"";

        byte[] dietaTxt = arquivo.getBytes(StandardCharsets.UTF_8);
        return dietaTxt;
    }

    public void agendarExecucao(String personId,byte[] arquivoLeitura){
        filaPersonId.insert(personId);
        filaObj.insert(arquivoLeitura);
    }


    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void rodarFilaAgendada() throws UnsupportedEncodingException {
        while (!filaObj.isEmpty()){
            byte[] dietaEmByte = filaObj.poll();
            String personId = filaPersonId.poll();
            this.lerArquivoTxt(dietaEmByte, personId);
        }
    }

    public void lerArquivoTxt(byte[] dietaImportada, String personId) throws UnsupportedEncodingException {

        Usuario usuario = usuarioRepository.findByPersonId(personId);
        if (usuario == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível encontrar o usuário");
        }
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nomeDieta = null , descricaoDieta = null, nomeIngrediente;
        int porcao;
        Double proteina, lipidio, carboidrato, sodio, caloria, quantidade;
        int contaRegDadoLido = 0;
        int qtdRegDadoGravado;
        Dieta dieta = new Dieta();
        DietaRequest dietaRequest = new DietaRequest();
        List<DietaRequest> listaLida = new ArrayList<>();
        List<IngredientesDieta> ingredientesDietas = new ArrayList<>();
        String dyteParaString = new String(dietaImportada, "UTF-8");

        entrada = new BufferedReader(new StringReader(dyteParaString));

        try{
            registro = entrada.readLine();
            while (registro != null){
                tipoRegistro = registro.substring(0,2);
                if (tipoRegistro.equals("00")){
                    System.out.println("Registro de header");
                    System.out.println("Tipo do arquivo: " + registro.substring(2,7));
                    System.out.println("Data e hora de gravação: " + registro.substring(7,26));
                    System.out.println("versão do documento: " + registro.substring(26,28));
                }
                else if (tipoRegistro.equals("01")){
                    System.out.println("Registo de Trailer");
                    qtdRegDadoGravado = Integer.parseInt(registro.substring(2,12));
                    if (contaRegDadoLido == qtdRegDadoGravado){
                        System.out.println("Quantidade de registros lidos é compatível com a quantidade de" +
                                "resgistros gravados");
                    }
                    else{
                        System.out.println("Quantidade de registros lidos NÂo é compatível com a quantidade de" +
                                "resgistros gravados");
                    }
                }
                else if (tipoRegistro.equals("02")){
                    System.out.println("Registro corpo");
                    nomeDieta = registro.substring(3,19).trim();
                    descricaoDieta = registro.substring(19,59).trim();
                    nomeIngrediente = registro.substring(59,79).trim();
                    porcao = Integer.parseInt(registro.substring(79, 86).trim().replace(',', '.'));
                    proteina = Double.parseDouble(registro.substring(86, 95).trim().replace(',', '.'));
                    lipidio = Double.valueOf(registro.substring(95,103).trim().replace(',','.'));
                    carboidrato = Double.valueOf(registro.substring(103,115).trim().replace(',','.'));
                    sodio = Double.valueOf(registro.substring(115,121).trim().replace(',','.'));
                    caloria = Double.valueOf(registro.substring(121,129).trim().replace(',','.'));
                    String imagem = Objects.requireNonNull(imagensPexelService.getImagemPexel(nomeDieta).getBody()).getPhotos().get(0).getSrc().getMedium();

                    dieta.setNome(nomeDieta);
                    dieta.setDescricao(descricaoDieta);
                    dieta.setImagem(imagem);
                    dieta.setCriador(usuario);
                    Ingrediente ingredienteSalvo = ingredienteRepository.save(new Ingrediente(nomeIngrediente,porcao,Double.valueOf(Math.round(proteina)),Double.valueOf(Math.round(lipidio)),Double.valueOf(Math.round(carboidrato)),Double.valueOf(Math.round(sodio)),Double.valueOf(Math.round(caloria))));
                    ingredientesDietas.add(new IngredientesDieta(dieta,ingredienteSalvo, 1.0));
                    contaRegDadoLido++;

                }else {
                    System.out.println("Tipo de registro invalido");
                }


                registro = entrada.readLine();
            }

            dietaRequest.setDieta(dieta);
            dietaRequest.setPersonId(usuario.getPersonId());
            dietaRequest.setIngredientesDieta(ingredientesDietas);
            this.cadastrar(dietaRequest);
            entrada.close();
        }
        catch (IOException erro){
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }
        System.out.println("\nConteúdo da lista do arquivo");
        for (DietaRequest txt : listaLida){
            System.out.println(txt);
        }
    }
}
