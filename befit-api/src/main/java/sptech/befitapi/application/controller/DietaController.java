package sptech.befitapi.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.befitapi.application.request.DietaRequest;
import sptech.befitapi.application.response.DietaCompleta;
import sptech.befitapi.application.service.DietaService;
import sptech.befitapi.resources.repository.entity.Dieta;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dietas")
public class DietaController {

    @Autowired
    private DietaService dietaService;

    @PostMapping
    public ResponseEntity<Dieta> cadastrar(@RequestBody DietaRequest dieta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dietaService.cadastrar(dieta));
    }

    @GetMapping
    public ResponseEntity<List<Dieta>> getAll() {
        List<Dieta> dietas = dietaService.getAll();

        return (!dietas.isEmpty()) ? ResponseEntity.status(HttpStatus.OK).body(dietas) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DietaCompleta>> get(@PathVariable int id) {
        Optional<DietaCompleta> dieta = dietaService.getById(id);

        return (dieta.isPresent()) ? ResponseEntity.status(HttpStatus.OK).body(dieta) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
