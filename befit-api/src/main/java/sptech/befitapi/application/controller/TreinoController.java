package sptech.befitapi.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.befitapi.application.request.TreinoRequest;
import sptech.befitapi.application.response.TreinoDetalhado;
import sptech.befitapi.application.response.TreinoFavoritoResponse;
import sptech.befitapi.application.service.TreinoService;
import sptech.befitapi.resources.repository.entity.Treino;
import sptech.befitapi.resources.repository.entity.TreinoFavorito;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/treinos")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @PostMapping
    public ResponseEntity<Treino> post(@RequestBody TreinoRequest treino) {
        return ResponseEntity.status(HttpStatus.CREATED).body(treinoService.save(treino));
    }

    @GetMapping
    public ResponseEntity<List<Treino>> getCatalogo() {
        List<Treino> treinos = treinoService.getAll();
        return (!treinos.isEmpty()) ? ResponseEntity.status(HttpStatus.OK).body(treinos) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/favoritos/{id}")
    public ResponseEntity<List<TreinoFavoritoResponse>> getFavorito(@PathVariable int id) {
        List<TreinoFavoritoResponse> treinos = treinoService.getFavoritos(id);
        return (treinos != null) ? ResponseEntity.status(HttpStatus.OK).body(treinos) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/favoritar/{usuarioId}/{treinoId}")
    public ResponseEntity<String> postFavoritar(@PathVariable int usuarioId, @PathVariable int treinoId) {
        Boolean favoritado = treinoService.favoritar(usuarioId, treinoId);

        return (favoritado) ? ResponseEntity.status(HttpStatus.CREATED).body("Treino favoritado com sucesso") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível favoitar o treino");

    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TreinoDetalhado>> getTreinoDetalhado(@PathVariable int id) {
        List<TreinoDetalhado> treinoDetalhado = treinoService.getTreinoDetalhado(id);
        return (treinoDetalhado != null) ? ResponseEntity.status(HttpStatus.OK).body(treinoDetalhado) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
