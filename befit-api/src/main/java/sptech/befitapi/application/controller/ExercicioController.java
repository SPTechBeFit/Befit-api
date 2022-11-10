package sptech.befitapi.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.befitapi.application.request.ExercicioRequest;
import sptech.befitapi.application.service.ExercicioService;
import sptech.befitapi.resources.repository.entity.Exercicio;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/exercicios")
public class ExercicioController {

    @Autowired
    ExercicioService exercicioService;

    @PostMapping
    public ResponseEntity<Exercicio> post(@RequestBody ExercicioRequest exercicio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exercicioService.save(exercicio));
    }
}
