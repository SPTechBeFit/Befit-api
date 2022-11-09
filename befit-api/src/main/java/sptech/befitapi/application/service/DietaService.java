package sptech.befitapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sptech.befitapi.application.request.DietaRequest;
import sptech.befitapi.application.response.DietaCompleta;
import sptech.befitapi.resources.repository.DietaRepository;
import sptech.befitapi.resources.repository.IngredientesDietaRepository;
import sptech.befitapi.resources.repository.UsuarioRepository;
import sptech.befitapi.resources.repository.entity.Dieta;
import sptech.befitapi.resources.repository.entity.IngredientesDieta;
import sptech.befitapi.resources.repository.entity.Usuario;

import java.util.List;
import java.util.Optional;

@Service
public class DietaService {

    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private IngredientesDietaRepository ingredientesDietaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Dieta cadastrar(@RequestBody DietaRequest dieta) {
        Usuario usuario = usuarioRepository.findByPersonId(dieta.getPersonId());

        if (usuario != null) {
            dieta.getDieta().setCriador(usuario);
        }

        Dieta dietaDB = dietaRepository.save(dieta.getDieta());

        for (IngredientesDieta ingrediente: dieta.getIngredientesDieta()) {
            ingrediente.setDieta(dietaDB);
            ingredientesDietaRepository.save(ingrediente);
        }

        return dietaDB;
    }

    public List<Dieta> getAll() {
        return dietaRepository.findAll();
    }

    public Optional<DietaCompleta> getById(int id) {
        List<IngredientesDieta> ingredientesDietas = ingredientesDietaRepository.findIngredientesDietaByDietaId(id);
        Optional<Dieta> dieta = dietaRepository.findById(id);

        for (IngredientesDieta i : ingredientesDietas) {
            i.getIngrediente().setPorcao((int) (i.getIngrediente().getPorcao() * i.getQuantidade()));
            i.getIngrediente().setProteina(i.getIngrediente().getProteina() * i.getQuantidade());
            i.getIngrediente().setLipidio(i.getIngrediente().getLipidio() * i.getQuantidade());
            i.getIngrediente().setCarboidrato(i.getIngrediente().getCarboidrato() * i.getQuantidade());
            i.getIngrediente().setSodio(i.getIngrediente().getSodio() * i.getQuantidade());
            i.getIngrediente().setCaloria(i.getIngrediente().getCaloria() * i.getQuantidade());
        }

        return Optional.of(new DietaCompleta(dieta, ingredientesDietas));
    }
}
