package sptech.befitapi.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.befitapi.application.request.DietaRequest;
import sptech.befitapi.application.response.CatalogoDietaResponse;
import sptech.befitapi.application.response.DietaCompleta;
import sptech.befitapi.application.response.DietaFavoritaResponse;
import sptech.befitapi.application.response.TreinoFavoritoResponse;
import sptech.befitapi.application.service.DietaService;
import sptech.befitapi.resources.repository.entity.Dieta;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dietas")
public class DietaController {

    @Autowired
    private DietaService dietaService;

    @PostMapping
    public ResponseEntity<Dieta> cadastrar(@RequestBody DietaRequest dieta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dietaService.cadastrar(dieta));
    }

    @GetMapping("/catalogo/{personId}")
    public ResponseEntity<List<CatalogoDietaResponse>> getAll(@PathVariable String personId) {

        return ResponseEntity.status(HttpStatus.OK).body(dietaService.getCatalogo(personId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietaCompleta> get(@PathVariable int id) {
        DietaCompleta dieta = dietaService.getById(id);

        return (dieta != null) ? ResponseEntity.status(HttpStatus.OK).body(dieta) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/favoritar/{personId}/{dietaId}")
    public ResponseEntity<String> postFavoritar(@PathVariable String personId, @PathVariable int dietaId) {
        Boolean favoritado = dietaService.favoritar(personId, dietaId);

        return (favoritado) ? ResponseEntity.status(HttpStatus.CREATED).body("Dieta favoritado com sucesso") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível favoitar a dieta");
    }

    @DeleteMapping("/desfavoritar/{personId}/{dietaId}")
    public ResponseEntity<String> deleteFavorito(@PathVariable String personId, @PathVariable int dietaId) {
        Boolean deletado = dietaService.deleteFavorito(personId, dietaId);

        return (deletado) ? ResponseEntity.status(HttpStatus.CREATED).body("Dieta desfavoritada com sucesso") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível desfavoritar a dieta");
    }

    @GetMapping("/favoritos/{personId}")
    public ResponseEntity<List<DietaFavoritaResponse>> getFavorito(@PathVariable String personId) {
        List<DietaFavoritaResponse> dietas = dietaService.getFavoritos(personId);
        return (dietas != null) ? ResponseEntity.status(HttpStatus.OK).body(dietas) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
