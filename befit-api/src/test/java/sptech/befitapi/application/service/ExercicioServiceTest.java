package sptech.befitapi.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import sptech.befitapi.application.domain.pexel.Pexels;
import sptech.befitapi.application.domain.pexel.Photo;
import sptech.befitapi.application.domain.pexel.Src;
import sptech.befitapi.application.request.ExercicioAtualizadoRequest;
import sptech.befitapi.application.request.ExercicioRequest;
import sptech.befitapi.resources.repository.ExercicioRepository;
import sptech.befitapi.resources.repository.entity.Exercicio;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExercicioServiceTest {

    @InjectMocks
    ExercicioService service;

    @Mock
    ExercicioRepository exercicioRepository;

    @Mock
    ImagensPexelService imagensPexelService;

    @Test
    void save_deveSalvarUmExercicioComSucesso() {
        Photo photo = new Photo();
        Src src = new Src();
        src.setMedium("imagemTest");
        photo.setSrc(src);

        Pexels pexels = new Pexels();
        pexels.setPage(1);
        pexels.setPer_page(1);
        pexels.setPhotos(List.of(photo));
        when(imagensPexelService.getImagemPexel(anyString())).thenReturn(ResponseEntity.ok(pexels));

        service.save(new ExercicioRequest("supino reto", "descricaoTest"));

        verify(exercicioRepository, times(1)).save(any());
    }

    @Test
    void update_deveAtualizarAImagemDoExercicioComSucesso() {
        when(exercicioRepository.findById(anyInt())).thenReturn(Optional.of(new Exercicio(1, "exercicioTest", "descricaoTest", "imagemTest")));

        Boolean resultado = service.update(1, new ExercicioAtualizadoRequest("imagemTest"));

        verify(exercicioRepository, times(1)).save(any());
        assertEquals(true, resultado);
    }

    @Test
    void update_deveNaoAtualizarAImagemDoExercicio() {
        when(exercicioRepository.findById(anyInt())).thenReturn(Optional.of(new Exercicio(1, "exercicioTest", "descricaoTest", "imagemTest")));
        when(exercicioRepository.save(any())).thenReturn(new Exception());

        Boolean resultado = service.update(1, new ExercicioAtualizadoRequest("imagemTest"));

        assertEquals(false, resultado);
    }

    @Test
    void update_deveLancarUmaNotFoundExceptionQuandoExercicioInvalido() {
        when(exercicioRepository.findById(anyInt())).thenReturn(Optional.empty());

        ResponseStatusException resultado = assertThrows(ResponseStatusException.class, () -> service.update(1, new ExercicioAtualizadoRequest("imagemTest")));
        verify(exercicioRepository, times(0)).save(any());
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatus());
        assertEquals("404 NOT_FOUND \"Não foi possível encontrar o exercicio\"", resultado.getMessage());
    }

    @Test
    void getAll_deveRetornarTodosOsExercicios() {
        when(exercicioRepository.findAll()).thenReturn(List.of(new Exercicio()));

        List<Exercicio> resultado = service.getAll();

        assertNotNull(resultado);
    }

}