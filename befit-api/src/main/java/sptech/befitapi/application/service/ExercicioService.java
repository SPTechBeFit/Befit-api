package sptech.befitapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.befitapi.application.request.ExercicioRequest;
import sptech.befitapi.resources.repository.ExercicioRepository;
import sptech.befitapi.resources.repository.entity.Exercicio;

import java.util.Objects;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private ImagensPexelService imagensPexelService;

    public Exercicio save(ExercicioRequest exercicio) {
        String imagem = Objects.requireNonNull(imagensPexelService.getImagemPexel("academia " + exercicio.getNome()).getBody()).getPhotos().get(0).getSrc().getMedium();
        return exercicioRepository.save(exercicio.toRepository(imagem));
    }
}
