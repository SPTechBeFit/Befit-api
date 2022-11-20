package sptech.befitapi.application.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sptech.befitapi.application.request.DietaRequest;
import sptech.befitapi.application.response.DietaCompleta;
import sptech.befitapi.application.service.DietaService;
import sptech.befitapi.resources.repository.IngredienteRepository;
import sptech.befitapi.resources.repository.UsuarioRepository;
import sptech.befitapi.resources.repository.entity.Dieta;
import sptech.befitapi.resources.repository.entity.Ingrediente;
import sptech.befitapi.resources.repository.entity.IngredientesDieta;
import sptech.befitapi.resources.repository.entity.Usuario;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
@EnableScheduling
public class ArquivoTxt {

    @Autowired
    DietaService dietaService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    IngredienteRepository ingredienteRepository;

    FilaObj<Optional<DietaCompleta>> filaObj = new FilaObj<>(20);

    public static void gravaRegistro(String registro, String nomeArq){
        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro){
            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
        }

        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro){
            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
        }

    }

    public String cabecalhoDietaTxt(){
        String corpo;
        corpo = "   ";
        corpo += String.format("%-15.15s ", "Nome");
        corpo += String.format("%-40.40s", "Descricao");
        corpo += String.format("%-20.20s", "Ingrediente");
        corpo += String.format("%-7.7s", "Porcao");
        corpo += String.format("%-9.9s", "Proteina");
        corpo += String.format("%-8.8s", "Lipidio");
        corpo += String.format("%-20.20s", "Carboidrato");
        corpo += String.format("%-6.6s", "Sodio");
        corpo += String.format("%-8.8s", "Caloria");
        corpo += String.format("%-11.11s", "Quantidade");
        return corpo;
    }

    public void gravarDietaTxt(Optional<DietaCompleta> dietaCompleta){
        int contaRegDados = 0;

        String header = "00DIETA";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        gravaRegistro(header, "Dieta");
        gravaRegistro(cabecalhoDietaTxt(),"Dieta");

        Optional<Dieta> dieta = dietaCompleta.get().getDieta();
        List<IngredientesDieta> ingredientes = dietaCompleta.get().getIngredientes();


        String corpo;
        for (int i = 0; i < ingredientes.size(); i++) {

            corpo = "02 ";
            //corpo += String.format("%-5.5s", d.getIdExercicio());
            corpo += String.format("%-15.15s ", dieta.get().getNome());
            corpo += String.format("%-40.40s",  dieta.get().getDescricao());
            corpo += String.format("%-20.20s", ingredientes.get(i).getIngrediente().getNome());
            corpo += String.format("%07d", ingredientes.get(i).getIngrediente().getPorcao());
            corpo += String.format("%09.2f", ingredientes.get(i).getIngrediente().getProteina());
            corpo += String.format("%08.2f", ingredientes.get(i).getIngrediente().getLipidio());
            corpo += String.format("%020.2f", ingredientes.get(i).getIngrediente().getCarboidrato());
            corpo += String.format("%06.2f", ingredientes.get(i).getIngrediente().getSodio());
            corpo += String.format("%08.2f", ingredientes.get(i).getIngrediente().getCaloria());
            corpo += String.format("%-11.11s", ingredientes.get(i).getQuantidade());
            gravaRegistro(corpo, "Dieta");
            contaRegDados++;

     }
        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);
        gravaRegistro(trailer, "Dieta");
    }

    public void agendarExecucao(Optional<DietaCompleta> dietaCompleta){
        filaObj.insert(dietaCompleta);
    }

    @Scheduled(fixedDelay = 15000)
    public void rodarFilaAgendada(){
            while (!filaObj.isEmpty()){
                Optional<DietaCompleta> dietaCompleta = filaObj.poll();
                this.gravarDietaTxt(dietaCompleta);

            }
    }

    public void lerArquivoTxt(String nomeArq, String personId){

        Usuario usuario = usuarioRepository.findByPersonId(personId);
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

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro){
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }

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
                    qtdRegDadoGravado = Integer.valueOf(registro.substring(2,12));
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
                    descricaoDieta = registro.substring(19,59);
                    nomeIngrediente = registro.substring(59,79);
                    porcao = Integer.parseInt(registro.substring(79, 86).replace(',', '.'));
                    proteina = Double.parseDouble(registro.substring(86, 95).replace(',', '.'));
                    lipidio = Double.valueOf(registro.substring(95,103).replace(',','.'));
                    carboidrato = Double.valueOf(registro.substring(103,123).replace(',','.'));
                    sodio = Double.valueOf(registro.substring(123,129).replace(',','.'));
                    caloria = Double.valueOf(registro.substring(129,137).replace(',','.'));
                    quantidade = Double.valueOf(registro.substring(137,140).replace(',','.'));

                    dieta.setNome(nomeDieta);
                    dieta.setDescricao(descricaoDieta);
                    dieta.setCriador(usuario);
                    Ingrediente ingredienteSalvo = ingredienteRepository.save(new Ingrediente(nomeIngrediente,porcao,Double.valueOf(Math.round(proteina)),Double.valueOf(Math.round(lipidio)),Double.valueOf(Math.round(carboidrato)),Double.valueOf(Math.round(sodio)),Double.valueOf(Math.round(caloria))));
                    ingredientesDietas.add(new IngredientesDieta(dieta,ingredienteSalvo,quantidade));
                    contaRegDadoLido++;

                }else {
                    System.out.println("Tipo de registro invalido");
                }


                registro = entrada.readLine();
            }

            dietaRequest.setDieta(dieta);
            dietaRequest.setPersonId(usuario.getPersonId());
            dietaRequest.setIngredientesDieta(ingredientesDietas);
            dietaService.cadastrar(dietaRequest);
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
