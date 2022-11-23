package sptech.befitapi.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import sptech.befitapi.application.request.DietaRequest;
import sptech.befitapi.application.response.CatalogoDietaResponse;
import sptech.befitapi.application.response.DietaCompleta;
import sptech.befitapi.application.response.DietaFavoritaResponse;
import sptech.befitapi.resources.repository.DietaFavoritaRepository;
import sptech.befitapi.resources.repository.DietaRepository;
import sptech.befitapi.resources.repository.IngredientesDietaRepository;
import sptech.befitapi.resources.repository.UsuarioRepository;
import sptech.befitapi.resources.repository.entity.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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

    @Mock
    private DietaFavoritaRepository dietaFavoritaRepository;

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

    @Test
    void getCatalogo_deveRetornarOCatalogoParaPersonIdValidoECatalogoFavoritado() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        DietaFavorita dietaFavorita = new DietaFavorita(usuario, new Dieta(1));

        when(dietaRepository.findAll()).thenReturn(List.of(new Dieta(1)));
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(usuario);
        when(dietaFavoritaRepository.findDietaByUsuarioId(anyInt())).thenReturn(List.of(dietaFavorita));

        List<CatalogoDietaResponse> resultado = service.getCatalogo("personIdTest");

        assertNotNull(resultado);
    }

    @Test
    void getCatalogo_deveRetornarOCatalogoParaPersonIdValidoECatalogoNaoFavoritado() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        DietaFavorita dietaFavorita = new DietaFavorita(usuario, new Dieta(2));

        when(dietaRepository.findAll()).thenReturn(List.of(new Dieta(1)));
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(usuario);
        when(dietaFavoritaRepository.findDietaByUsuarioId(anyInt())).thenReturn(List.of(dietaFavorita));

        List<CatalogoDietaResponse> resultado = service.getCatalogo("personIdTest");

        assertNotNull(resultado);
    }

    @Test
    void getCatalogo_deveLancarUmaExceptionDeNotFoundParaDietas() {
        when(dietaRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseStatusException resultado = assertThrows(ResponseStatusException.class, () -> service.getCatalogo("personIdTest"));
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatus());
        assertEquals("404 NOT_FOUND \"Não foi possível encontrar dietas\"", resultado.getMessage());
    }

    @Test
    void getCatalogo_getCatalogo_deveLancarUmaExceptionDeNotFoundParaUsuario() {
        when(dietaRepository.findAll()).thenReturn(List.of(new Dieta(1)));
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(null);

        ResponseStatusException resultado = assertThrows(ResponseStatusException.class, () -> service.getCatalogo("personIdTest"));
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatus());
        assertEquals("404 NOT_FOUND \"Não foi possível encontrar o usuário\"", resultado.getMessage());
    }

    @Test
    void getById_deveRetornarUmaDietaCompleta() {
        IngredientesDieta ingredientesDieta = new IngredientesDieta();
        ingredientesDieta.setId(1);
        ingredientesDieta.setDieta(new Dieta(1));

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(1);
        ingrediente.setPorcao(100);
        ingrediente.setProteina(100.00);
        ingrediente.setLipidio(100.00);
        ingrediente.setCarboidrato(100.00);
        ingrediente.setSodio(100.00);
        ingrediente.setCaloria(1000.00);
        ingredientesDieta.setIngrediente(ingrediente);

        ingredientesDieta.setQuantidade(2.0);


        when(ingredientesDietaRepository.findIngredientesDietaByDietaId(anyInt())).thenReturn(List.of(ingredientesDieta));

        DietaCompleta resultado = service.getById(1);

        assertNotNull(resultado);
    }

    @Test
    void getById_deveLancarUmaExceptionDeNotFoundParaIngredienteVazio() {
        when(ingredientesDietaRepository.findIngredientesDietaByDietaId(anyInt())).thenReturn(new ArrayList<>());

        ResponseStatusException resultado = assertThrows(ResponseStatusException.class, () -> service.getById(1));
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatus());
        assertEquals("404 NOT_FOUND \"Não foi possível encontrar os ingredientes da dieta\"", resultado.getMessage());
    }

    @Test
    void favoritar_deveFavoritarUmaDieta() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        when(usuarioRepository.findByPersonId(anyString())).thenReturn(usuario);
        when(dietaRepository.existsById(anyInt())).thenReturn(true);

        Boolean resultado = service.favoritar("personIdTest", 1);

        verify(dietaFavoritaRepository, times(1)).save(any());
        assertEquals(true, resultado);
    }

    @Test
    void favoritar_deveRetornarFalseQuandoUsuarioInvalido() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(null);

        Boolean resultado = service.favoritar("personIdTest", 1);

        verify(dietaFavoritaRepository, times(0)).save(any());
        assertEquals(false, resultado);
    }

    @Test
    void favoritar_deveRetornarFalseQuandoDietaInvalida() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        when(usuarioRepository.findByPersonId(anyString())).thenReturn(usuario);
        when(dietaRepository.existsById(anyInt())).thenReturn(false);

        Boolean resultado = service.favoritar("personIdTest", 1);

        verify(dietaFavoritaRepository, times(0)).save(any());
        assertEquals(false, resultado);
    }

    @Test
    void deleteFavorito_deveDeletarADietaFavoritaComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        DietaFavorita dietaFavorita = new DietaFavorita();
        dietaFavorita.setId(1);

        when(usuarioRepository.findByPersonId(anyString())).thenReturn(usuario);
        when(dietaFavoritaRepository.findDietaFavoritaByUsuarioIdAndDietaId(anyInt(), anyInt())).thenReturn(dietaFavorita);

        Boolean resultado = service.deleteFavorito("personIdTest", 1);

        verify(dietaFavoritaRepository, times(1)).deleteDietaFavoritaById(anyInt());
        assertEquals(true, resultado);
    }

    @Test
    void deleteFavorito_deveRetornarFalseQuandoUsuarioInvalido() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        when(usuarioRepository.findByPersonId(anyString())).thenReturn(usuario);
        when(dietaFavoritaRepository.findDietaFavoritaByUsuarioIdAndDietaId(anyInt(), anyInt())).thenReturn(null);


        Boolean resultado = service.deleteFavorito("personIdTest", 1);

        verify(dietaFavoritaRepository, times(0)).deleteDietaFavoritaById(anyInt());
        assertEquals(false, resultado);
    }

    @Test
    void deleteFavorito_deveRetornarFalseQuandoDietaInvalido() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(null);

        Boolean resultado = service.deleteFavorito("personIdTest", 1);

        verify(dietaFavoritaRepository, times(0)).deleteDietaFavoritaById(anyInt());
        assertEquals(false, resultado);
    }

    @Test
    void getFavoritos_deveRetornarDietaFavorita() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        when(usuarioRepository.findByPersonId(anyString())).thenReturn(usuario);

        when(dietaFavoritaRepository.findDietaByUsuarioId(anyInt())).thenReturn(List.of(new DietaFavorita(
                usuario,
                new Dieta(1, "dietaTest", "descricaoTest", "imagemTest", usuario)
                )));

        List<DietaFavoritaResponse> resultado = service.getFavoritos("personIdTest");

        assertFalse(resultado.isEmpty());
    }

    @Test
    void getFavoritos_deveLancarUmaExceptionDeNotFoundParaUsuarioInvalido() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(null);

        ResponseStatusException resultado = assertThrows(ResponseStatusException.class, () -> service.getFavoritos("personIdTest"));
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatus());
        assertEquals("404 NOT_FOUND \"Não foi possível encontrar o usuário\"", resultado.getMessage());

    }

    @Test
    void getFavoritos_deveRetornarNullQuandoDietaVazia() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        when(usuarioRepository.findByPersonId(anyString())).thenReturn(usuario);
        when(dietaFavoritaRepository.findDietaByUsuarioId(anyInt())).thenReturn(new ArrayList<>());

        List<DietaFavoritaResponse> resultado = service.getFavoritos("personIdTest");

        assertNull(resultado);
    }

}