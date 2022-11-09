package sptech.befitapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.befitapi.resources.repository.UsuarioRepository;
import sptech.befitapi.resources.repository.entity.*;

import java.util.List;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> usuarios(){
            return usuarioRepository.findAll();
    }

    public Usuario cadastrar(Usuario usuario) {
            usuarioRepository.save(usuario);
            return usuario;
    }

    public Usuario login(String email, String senha) {
            Usuario usuario = usuarioRepository.findUsuarioByEmailAndSenha(email, senha);

            if(usuario == null || usuario.getLogado()) {
                return null;
            }

            usuario.setLogado(true);

            usuarioRepository.save(usuario);

            return usuario;

    }

    public Usuario logout(String personId) {
            Usuario usuario = usuarioRepository.findByPersonId(personId);

            if(usuario == null || !usuario.getLogado()) {
                return null;
            }

            usuario.setLogado(false);

            usuarioRepository.save(usuario);

            return usuario;
    }
}
