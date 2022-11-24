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
import sptech.befitapi.application.request.SerieRequest;
import sptech.befitapi.application.request.TreinoRequest;
import sptech.befitapi.application.response.CatalogoTreinoResponse;
import sptech.befitapi.application.response.TreinoDetalhado;
import sptech.befitapi.application.response.TreinoFavoritoResponse;
import sptech.befitapi.resources.repository.SerieRepository;
import sptech.befitapi.resources.repository.TreinoFavoritoRepository;
import sptech.befitapi.resources.repository.TreinoRepository;
import sptech.befitapi.resources.repository.UsuarioRepository;
import sptech.befitapi.resources.repository.entity.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreinoServiceTest {

    @InjectMocks
    TreinoService service;

    @Mock
    private TreinoRepository treinoRepository;

    @Mock
    private ImagensPexelService imagensPexelService;

    @Mock
    private SerieRepository serieRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TreinoFavoritoRepository treinoFavoritoRepository;

    @Test
    void save_deveSalvarOTreino() {
        Photo photo = new Photo();
        Src src = new Src();
        src.setMedium("imagemTest");
        photo.setSrc(src);

        Pexels pexels = new Pexels();
        pexels.setPage(1);
        pexels.setPer_page(1);
        pexels.setPhotos(List.of(photo));

        TreinoRequest treinoRequest = new TreinoRequest();
        treinoRequest.setNome("nomeTest");
        treinoRequest.setDescricao("descricaoTest");
        treinoRequest.setPersonId("personIdTest");

        SerieRequest serieRequest = new SerieRequest();
        serieRequest.setExercicioId(1);
        treinoRequest.setSeries(List.of(serieRequest));

        when(imagensPexelService.getImagemPexel(anyString())).thenReturn(ResponseEntity.ok(pexels));
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(new Usuario());
        when(treinoRepository.save(any())).thenReturn(new Treino(1));

        Treino resultado = service.save(treinoRequest);

        verify(treinoRepository, times(1)).save(any());
        verify(serieRepository, times(1)).save(any());
        assertNotNull(resultado);
    }

    @Test
    void save_deveLancarNotFoundExceptionQuandoUsuarioInvalido() {
        TreinoRequest treinoRequest = new TreinoRequest();
        treinoRequest.setPersonId("personIdTest");

        when(usuarioRepository.findByPersonId(anyString())).thenReturn(null);

        ResponseStatusException resultado = assertThrows(ResponseStatusException.class, () -> service.save(treinoRequest));
        verify(treinoRepository, times(0)).save(any());
        verify(serieRepository, times(0)).save(any());
        verify(imagensPexelService, times(0)).getImagemPexel(anyString());
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatus());
        assertEquals("404 NOT_FOUND \"Não foi possível encontrar o usuário\"", resultado.getMessage());

    }

    @Test
    void getCatalogo_deveRetornarOcatalogoDeTreinoComSucessoComFavoritadoTrue() {
        Usuario usuario = new Usuario(1);

        Treino treino = new Treino();
        treino.setId(1);
        treino.setNome("treinoTest");
        treino.setDescricao("descricaoTest");
        treino.setImagem("imagemTest");
        treino.setCriador(usuario);

        TreinoFavorito treinoFavorito = new TreinoFavorito();
        treinoFavorito.setId(1);
        treinoFavorito.setTreino(treino);
        treinoFavorito.setUsuario(usuario);

        when(treinoRepository.findAll()).thenReturn(List.of(treino));
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(usuario);
        when(treinoFavoritoRepository.findTreinoByUsuarioId(anyInt())).thenReturn(List.of(treinoFavorito));

        List<CatalogoTreinoResponse> resultado = service.getCatalogo("personIdTest");

        assertNotNull(resultado);
        assertEquals(true, resultado.get(0).getFavoritado());
    }

    @Test
    void getCatalogo_deveRetornarOcatalogoDeTreinoComSucessoComFavoritadoFalse() {
        Usuario usuario = new Usuario(1);

        Treino treino = new Treino();
        treino.setId(1);
        treino.setNome("treinoTest");
        treino.setDescricao("descricaoTest");
        treino.setImagem("imagemTest");
        treino.setCriador(usuario);

        Treino treino2 = new Treino();
        treino.setId(2);
        treino.setNome("treinoTest");
        treino.setDescricao("descricaoTest");
        treino.setImagem("imagemTest");
        treino.setCriador(usuario);

        TreinoFavorito treinoFavorito = new TreinoFavorito();
        treinoFavorito.setId(1);
        treinoFavorito.setTreino(treino2);
        treinoFavorito.setUsuario(usuario);

        when(treinoRepository.findAll()).thenReturn(List.of(treino));
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(usuario);
        when(treinoFavoritoRepository.findTreinoByUsuarioId(anyInt())).thenReturn(List.of(treinoFavorito));

        List<CatalogoTreinoResponse> resultado = service.getCatalogo("personIdTest");

        assertNotNull(resultado);
        assertEquals(false, resultado.get(0).getFavoritado());
    }

    @Test
    void getCatalogo_deveRetornarNullQuandoTreinoVazio() {
        when(treinoRepository.findAll()).thenReturn(new ArrayList<>());
        List<CatalogoTreinoResponse> resultado = service.getCatalogo("personIdTest");

        assertNull(resultado);
    }

    @Test
    void getCatalogo_deveRetornarNotFoundExceptionQuandoUsuarioInvalido() {
        Treino treino = new Treino();
        treino.setId(1);
        treino.setNome("treinoTest");
        treino.setDescricao("descricaoTest");
        treino.setImagem("imagemTest");
        treino.setCriador(new Usuario(1));

        when(treinoRepository.findAll()).thenReturn(List.of(treino));
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(null);

        ResponseStatusException resultado = assertThrows(ResponseStatusException.class, () -> service.getCatalogo("personIdTest"));
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatus());
        assertEquals("404 NOT_FOUND \"Não foi possível encontrar o usuário\"", resultado.getMessage());
    }

    @Test
    void getFavoritos_deveRetornarOsTreinosFavoritos() {
        Usuario usuario = new Usuario(1);

        Treino treino = new Treino();
        treino.setId(1);
        treino.setNome("treinoTest");
        treino.setDescricao("descricaoTest");
        treino.setImagem("imagemTest");
        treino.setCriador(usuario);

        TreinoFavorito treinoFavorito = new TreinoFavorito();
        treinoFavorito.setId(1);
        treinoFavorito.setTreino(treino);
        treinoFavorito.setUsuario(usuario);

        when(usuarioRepository.findByPersonId(anyString())).thenReturn(usuario);
        when(treinoFavoritoRepository.findTreinoByUsuarioId(anyInt())).thenReturn(List.of(treinoFavorito));

        List<TreinoFavoritoResponse> resultado = service.getFavoritos("personIdTest");

        assertNotNull(resultado);
    }

    @Test
    void getFavoritos_deveRetornarNotFoundExceptionQuandoUsuarioInvalido() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(null);

        ResponseStatusException resultado = assertThrows(ResponseStatusException.class, () -> service.getFavoritos("personIdTest"));
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatus());
        assertEquals("404 NOT_FOUND \"Não foi possível encontrar o usuário\"", resultado.getMessage());

    }

    @Test
    void getFavoritos_deveRetornarNullQuandoTreinoVazio() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(new Usuario(1));
        when(treinoFavoritoRepository.findTreinoByUsuarioId(anyInt())).thenReturn(new ArrayList<>());

        List<TreinoFavoritoResponse> resultado = service.getFavoritos("personIdTest");

        assertNull(resultado);
    }

    @Test
    void favoritar_deveFavoritarOTreino() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(new Usuario(1));

        Boolean resultado = service.favoritar("personIdTest", 1);

        verify(treinoFavoritoRepository, times(1)).save(any());
        assertEquals(true, resultado);
    }

    @Test
    void favoritar_deveRetornarFalseQuandoUsuarioInvalido() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(null);

        Boolean resultado = service.favoritar("personIdTest", 1);

        verify(treinoFavoritoRepository, times(0)).save(any());
        assertEquals(false, resultado);
    }

    @Test
    void favoritar_deveRetornarFalseQuandoErroNoBancoDeDados() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(new Usuario(1));
        when(treinoFavoritoRepository.save(any())).thenReturn(new Exception());

        Boolean resultado = service.favoritar("personIdTest", 1);

        verify(treinoFavoritoRepository, times(1)).save(any());
        assertEquals(false, resultado);
    }

    @Test
    void deleteFavorito_deveDesfavoritarOTreinoComSucesso() {
        TreinoFavorito treinoFavorito = new TreinoFavorito();
        treinoFavorito.setId(1);

        when(usuarioRepository.findByPersonId(anyString())).thenReturn(new Usuario(1));
        when(treinoFavoritoRepository.findTreinoFavoritoByUsuarioIdAndTreinoId(anyInt(), anyInt())).thenReturn(treinoFavorito);

        Boolean resultado = service.deleteFavorito("personIdTest", 1);

        verify(treinoFavoritoRepository, times(1)).deleteTreinoFavoritoById(anyInt());
        assertEquals(true, resultado);
    }

    @Test
    void deleteFavorito_deveRetornarFalseQuandoUsuarioInvalido() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(null);

        Boolean resultado = service.deleteFavorito("personIdTest", 1);

        verify(treinoFavoritoRepository, times(0)).deleteTreinoFavoritoById(anyInt());
        assertEquals(false, resultado);
    }

    @Test
    void deleteFavorito_deveRetornarFalseQuandoTreinoFavoritoVazio() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(new Usuario(1));
        when(treinoFavoritoRepository.findTreinoFavoritoByUsuarioIdAndTreinoId(anyInt(), anyInt())).thenReturn(null);

        Boolean resultado = service.deleteFavorito("personIdTest", 1);

        verify(treinoFavoritoRepository, times(0)).deleteTreinoFavoritoById(anyInt());
        assertEquals(false, resultado);
    }

    @Test
    void getTreinoDetalhado_deveRetornarOTreinoDetalhadoComSucesso() {
        Treino treino = new Treino();
        treino.setId(1);
        treino.setNome("treinoTest");
        treino.setDescricao("descricaoTest");
        treino.setImagem("imagemTest");
        treino.setCriador(new Usuario(1));

        Exercicio exercicio = new Exercicio(1, "exercicioTest", "descricaoTest", "imagemTest");

        Serie serie = new Serie();
        serie.setId(1);
        serie.setTreino(treino);
        serie.setExercicio(exercicio);

        when(serieRepository.findByTreinoId(anyInt())).thenReturn(List.of(serie));

        List<TreinoDetalhado> resultado = service.getTreinoDetalhado(1);

        assertNotNull(resultado);
    }

    @Test
    void getTreinoDetalhado_deveRetornarNullQuandoSerieVazia() {
        when(serieRepository.findByTreinoId(anyInt())).thenReturn(new ArrayList<>());

        List<TreinoDetalhado> resultado = service.getTreinoDetalhado(1);

        assertNull(resultado);
    }

    @Test
    void listaParaDesfazer_deveAdicionarNaListaSerieComSucesso() {
        when(serieRepository.findByTreinoId(anyInt())).thenReturn(List.of(new Serie()));

        service.listaParaDesfazer(new Treino(1));

        verify(serieRepository, times(1)).findByTreinoId(anyInt());
    }

    @Test
    void desfazer_deveDesfazerOsTreinosComSucesso() {
        Boolean resultado = service.desfazer();

        verify(serieRepository, times(1)).delete(any());
        verify(treinoRepository, times(1)).delete(any());
        assertEquals(true, resultado);
    }
}