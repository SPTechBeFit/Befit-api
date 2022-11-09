package sptech.befitapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.befitapi.application.request.TreinoRequest;
import sptech.befitapi.resources.repository.SerieRepository;
import sptech.befitapi.resources.repository.TreinoRepository;
import sptech.befitapi.resources.repository.entity.Treino;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private SerieRepository serieRepository;

    public Treino save(TreinoRequest treino) {
        Treino treinoDB = treinoRepository.save(treino.toTreinoRepository());

        System.out.println(treino.getSeries());

        treino.getSeries().forEach(
                serie -> serieRepository.save(serie.toSerieRepository(treinoDB.getId()))
        );

        return treinoDB;
    }
}
