package sptech.befitapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.befitapi.application.request.ExercicioAtualizadoRequest;
import sptech.befitapi.application.request.ExercicioRequest;
import sptech.befitapi.resources.repository.ExercicioRepository;
import sptech.befitapi.resources.repository.entity.Exercicio;
import sptech.befitapi.resources.repository.entity.Treino;
import sptech.befitapi.resources.repository.entity.TreinoFavorito;
import sptech.befitapi.resources.repository.entity.Usuario;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Boolean update(int id, ExercicioAtualizadoRequest imagem) {
        Optional<Exercicio> exericio = exercicioRepository.findById(id);

        if (!exericio.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível encontrar o exercicio"
            );
        }

        try{
            exercicioRepository.save(new Exercicio(exericio.get().getId(), exericio.get().getNome(), exericio.get().getDescricao(), imagem.getImagem()));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public List<Exercicio> getAll() {
        return exercicioRepository.findAll();
    }
}
