package sptech.befitapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.befitapi.application.request.TreinoRequest;
import sptech.befitapi.application.response.TreinoDetalhado;
import sptech.befitapi.application.response.TreinoFavoritoResponse;
import sptech.befitapi.resources.repository.SerieRepository;
import sptech.befitapi.resources.repository.TreinoFavoritoRepository;
import sptech.befitapi.resources.repository.TreinoRepository;
import sptech.befitapi.resources.repository.UsuarioRepository;
import sptech.befitapi.resources.repository.entity.Serie;
import sptech.befitapi.resources.repository.entity.Treino;
import sptech.befitapi.resources.repository.entity.TreinoFavorito;
import sptech.befitapi.resources.repository.entity.Usuario;

import java.util.List;
import java.util.Objects;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private ImagensPexelService imagensPexelService;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TreinoFavoritoRepository treinoFavoritoRepository;

    public Treino save(TreinoRequest treino) {
        if(!usuarioRepository.existsById(treino.getCriadorId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível encontrar o usuário"
            );
        }

        String imagem = Objects.requireNonNull(imagensPexelService.getImagemPexel("academia " + treino.getNome()).getBody()).getPhotos().get(0).getSrc().getMedium();

        Treino treinoDB = treinoRepository.save(treino.toTreinoRepository(imagem));

        System.out.println(treino.getSeries());

        treino.getSeries().forEach(
                serie -> serieRepository.save(serie.toSerieRepository(treinoDB.getId()))
        );

        return treinoDB;
    }

    public List<Treino> getAll() {
        return treinoRepository.findAll();
    }

    public List<TreinoFavoritoResponse> getFavoritos(int id) {

        if(!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível encontrar o usuário"
            );
        }

        List<TreinoFavorito> treinos = treinoFavoritoRepository.findTreinoByUsuarioId(id);
        if (treinos == null || treinos.isEmpty()) {
            return null;
        }
        return new TreinoFavoritoResponse().fromTreinoFavoritoRepository(treinos);
    }

    public Boolean favoritar(int usuarioId, int treinoId) {
        try{
            treinoFavoritoRepository.save(new TreinoFavorito(new Usuario(usuarioId), new Treino(treinoId)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<TreinoDetalhado> getTreinoDetalhado(int id) {
        List<Serie> series = serieRepository.findByTreinoId(id);
        if (series == null || series.isEmpty()) {
            return null;
        }
        return new TreinoDetalhado().fromSerieRepository(series);
    }
}
