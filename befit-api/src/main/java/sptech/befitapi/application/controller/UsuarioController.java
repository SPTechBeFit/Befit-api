package sptech.befitapi.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.befitapi.application.service.UsuarioService;
import sptech.befitapi.resources.repository.entity.Usuario;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> usuarios() {
        List<Usuario> usuarios = usuarioService.usuarios();

        return (!usuarios.isEmpty()) ? ResponseEntity.status(HttpStatus.OK).body(usuarios) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrar(usuario));
    }

    @PatchMapping("/login/{email}/{senha}")
    public ResponseEntity<Usuario> login(@PathVariable String email, @PathVariable String senha) {

        Usuario usuario = usuarioService.login(email, senha);

        return (usuario != null) ? ResponseEntity.status(HttpStatus.OK).body(usuario) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/logout/{personId}")
    public ResponseEntity<Usuario> logout(@PathVariable String personId) {

        Usuario usuario = usuarioService.logout(personId);

        return (usuario != null) ? ResponseEntity.status(HttpStatus.OK).body(usuario) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
