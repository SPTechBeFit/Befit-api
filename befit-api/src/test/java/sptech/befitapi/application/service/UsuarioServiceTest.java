package sptech.befitapi.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sptech.befitapi.resources.repository.UsuarioRepository;
import sptech.befitapi.resources.repository.entity.Usuario;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    UsuarioService service;

    @Mock
    UsuarioRepository usuarioRepository;

    @Test
    void usuarios_deveRetornarTodosOsUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(new Usuario()));

        List<Usuario> resultado = service.usuarios();

        assertNotNull(resultado);
    }

    @Test
    void cadastrar_deveCadastrarOUsuario() {
        Usuario resultado = service.cadastrar(new Usuario(1));

        verify(usuarioRepository, times(1)).save(any());
        assertNotNull(resultado);
    }

    @Test
    void login_deveRetornarOUsuarioComOLogadoComoTrue() {
        when(usuarioRepository.findUsuarioByEmailAndSenha(anyString(), anyString())).thenReturn(new Usuario(1));

        Usuario resultado = service.login("emailTest", "senhaTest");

        verify(usuarioRepository, times(1)).save(any());
        assertNotNull(resultado);
        assertEquals(true, resultado.getLogado());
    }

    @Test
    void login_deveRetornarNullQuandoNaoEncontrarOUsuario() {
        when(usuarioRepository.findUsuarioByEmailAndSenha(anyString(), anyString())).thenReturn(null);

        Usuario resultado = service.login("emailTest", "senhaTest");

        verify(usuarioRepository, times(0)).save(any());
        assertNull(resultado);
    }

    @Test
    void logout_deveRetornarUsuarioComLogadoFalse() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(new Usuario(1));

        Usuario resultado = service.logout("personIdTest");

        verify(usuarioRepository, times(1)).save(any());
        assertNotNull(resultado);
        assertEquals(false, resultado.getLogado());
    }

    @Test
    void logout_deveRetornarNullQuandoNaoEncontrarOUsuario() {
        when(usuarioRepository.findByPersonId(anyString())).thenReturn(null);

        Usuario resultado = service.logout("personIdTest");

        verify(usuarioRepository, times(0)).save(any());
        assertNull(resultado);
    }
}