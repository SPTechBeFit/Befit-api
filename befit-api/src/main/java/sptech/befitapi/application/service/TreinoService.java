package sptech.befitapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.befitapi.application.request.TreinoRequest;
import sptech.befitapi.resources.repository.SerieRepository;
import sptech.befitapi.resources.repository.TreinoRepository;
import sptech.befitapi.resources.repository.entity.Treino;

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

    public Treino save(TreinoRequest treino) {
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
}
