package sptech.befitapi.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.befitapi.application.listaObj.ListaObj;
import sptech.befitapi.application.service.IngredienteService;
import sptech.befitapi.resources.repository.entity.Ingrediente;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @PostMapping
    public ResponseEntity<Ingrediente> cadastrar( @RequestBody Ingrediente ingrediente) {

        return ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.cadastrar(ingrediente));
    }

    @GetMapping
    public ResponseEntity<List<Ingrediente>> getAll() {
        List<Ingrediente> ingredientes = ingredienteService.getAll();

        return (!ingredientes.isEmpty()) ? ResponseEntity.status(HttpStatus.OK).body(ingredientes) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ingrediente>> get(@PathVariable int id) {
        Optional<Ingrediente> ingrediente = ingredienteService.get(id);

        return (ingrediente.isPresent()) ? ResponseEntity.status(HttpStatus.OK).body(ingrediente) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/csv")
    public ResponseEntity<String> gerarCSV() {
        ingredienteService.gerarCsv();

    return ResponseEntity.status(200).body("CSV gerado com sucesso");

    }

}
