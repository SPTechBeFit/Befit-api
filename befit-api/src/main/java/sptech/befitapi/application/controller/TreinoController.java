package sptech.befitapi.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.befitapi.application.request.TreinoRequest;
import sptech.befitapi.application.service.TreinoService;
import sptech.befitapi.resources.repository.entity.Treino;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @PostMapping
    public ResponseEntity<Treino> post(@RequestBody TreinoRequest treino) {
        return ResponseEntity.status(HttpStatus.CREATED).body(treinoService.save(treino));
    }
}
