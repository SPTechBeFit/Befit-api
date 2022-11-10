package sptech.befitapi.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.befitapi.application.request.TreinoRequest;
import sptech.befitapi.application.service.TreinoService;
import sptech.befitapi.resources.repository.entity.Ingrediente;
import sptech.befitapi.resources.repository.entity.Treino;

import java.util.List;

@RestController
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
}
