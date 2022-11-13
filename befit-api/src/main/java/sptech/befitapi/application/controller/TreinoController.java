package sptech.befitapi.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.befitapi.application.request.TreinoRequest;
import sptech.befitapi.application.response.CatalogoTreinoResponse;
import sptech.befitapi.application.response.TreinoDetalhado;
import sptech.befitapi.application.response.TreinoFavoritoResponse;
import sptech.befitapi.application.service.TreinoService;
import sptech.befitapi.resources.repository.entity.Treino;

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

    @GetMapping("/catalogo/{personId}")
    public ResponseEntity<List<CatalogoTreinoResponse>> getCatalogo(@PathVariable String personId) {
        List<CatalogoTreinoResponse> catalago = treinoService.getCatalogo(personId);
        return (catalago != null) ? ResponseEntity.status(HttpStatus.OK).body(catalago) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/favoritos/{personId}")
    public ResponseEntity<List<TreinoFavoritoResponse>> getFavorito(@PathVariable String personId) {
        List<TreinoFavoritoResponse> treinos = treinoService.getFavoritos(personId);
        return (treinos != null) ? ResponseEntity.status(HttpStatus.OK).body(treinos) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/favoritar/{personId}/{treinoId}")
    public ResponseEntity<String> postFavoritar(@PathVariable String personId, @PathVariable int treinoId) {
        Boolean favoritado = treinoService.favoritar(personId, treinoId);

        return (favoritado) ? ResponseEntity.status(HttpStatus.CREATED).body("Treino favoritado com sucesso") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível favoitar o treino");

    }

    @DeleteMapping("/desfavoritar/{personId}/{treinoId}")
    public ResponseEntity<String> deleteFavorito(@PathVariable String personId, @PathVariable int treinoId) {
        Boolean apagado = treinoService.deleteFavorito(personId, treinoId);

        return (apagado) ? ResponseEntity.status(HttpStatus.CREATED).body("Treino deletado com sucesso") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível deletar o treino");

    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TreinoDetalhado>> getTreinoDetalhado(@PathVariable int id) {
        List<TreinoDetalhado> treinoDetalhado = treinoService.getTreinoDetalhado(id);
        return (treinoDetalhado != null) ? ResponseEntity.status(HttpStatus.OK).body(treinoDetalhado) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
