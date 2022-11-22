package sptech.befitapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import sptech.befitapi.application.request.DietaRequest;
import sptech.befitapi.application.response.*;
import sptech.befitapi.resources.repository.DietaFavoritaRepository;
import sptech.befitapi.resources.repository.DietaRepository;
import sptech.befitapi.resources.repository.IngredientesDietaRepository;
import sptech.befitapi.resources.repository.UsuarioRepository;
import sptech.befitapi.resources.repository.entity.*;

import java.util.ArrayList;
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

    @Autowired
    private DietaFavoritaRepository dietaFavoritaRepository;

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
        List<IngredientesDieta> ingredientesDietas = ingredientesDietaRepository.findIngredientesDietaByDietaId(id);

        if (ingredientesDietas.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível encontrar os ingredientes da dieta"
            );
        }

        for (IngredientesDieta i : ingredientesDietas) {
            i.getIngrediente().setPorcao((int) (i.getIngrediente().getPorcao() * i.getQuantidade()));
            i.getIngrediente().setProteina(i.getIngrediente().getProteina() * i.getQuantidade());
            i.getIngrediente().setLipidio(i.getIngrediente().getLipidio() * i.getQuantidade());
            i.getIngrediente().setCarboidrato(i.getIngrediente().getCarboidrato() * i.getQuantidade());
            i.getIngrediente().setSodio(i.getIngrediente().getSodio() * i.getQuantidade());
            i.getIngrediente().setCaloria(i.getIngrediente().getCaloria() * i.getQuantidade());
        }

        return new DietaCompleta(ingredientesDietas);
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
}
