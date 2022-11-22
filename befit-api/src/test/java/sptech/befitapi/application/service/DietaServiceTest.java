package sptech.befitapi.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import sptech.befitapi.application.request.DietaRequest;
import sptech.befitapi.resources.repository.DietaRepository;
import sptech.befitapi.resources.repository.IngredientesDietaRepository;
import sptech.befitapi.resources.repository.UsuarioRepository;
import sptech.befitapi.resources.repository.entity.Dieta;
import sptech.befitapi.resources.repository.entity.IngredientesDieta;
import sptech.befitapi.resources.repository.entity.Usuario;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DietaServiceTest {

    @InjectMocks
    DietaService service = new DietaService();

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private DietaRepository dietaRepository;

    @Mock
    private IngredientesDietaRepository ingredientesDietaRepository;

    @Test
    void cadastrar_deveCadastrarDietaCorretamente() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(new Usuario(
                1, "nomeTest", "email@test.com", "senhaTest", "d126b367-253b-47f3-84e2-601bbd90e839", 0, false
        ));

        when(dietaRepository.save(any())).thenReturn(new Dieta(1));

        when(ingredientesDietaRepository.save(any())).thenReturn(new IngredientesDieta());

        Dieta resultado = service.cadastrar(new DietaRequest(
                new Dieta(1),
                "d126b367-253b-47f3-84e2-601bbd90e839",
                List.of(new IngredientesDieta())
                ));

        assertNotNull(resultado);
    }

    @Test
    void cadastrar_deveLancarUmaExceptionDeNotFound() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(null);
        DietaRequest dietaRequest = new DietaRequest();
        dietaRequest.setPersonId("d126b367-253b-47f3-84e2-601bbd90e839");

        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> service.cadastrar(dietaRequest));
        assertEquals(HttpStatus.NOT_FOUND, result.getStatus());
    }

}