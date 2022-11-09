package sptech.befitapi.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.befitapi.application.domain.pexel.Pexels;
import sptech.befitapi.application.service.ImagensPexelService;

@RestController
@RequestMapping("imagem")
@Slf4j
public class ImagemPexelsController {

    private final ImagensPexelService imagensPexelService;

    public ImagemPexelsController(ImagensPexelService imagensPexelService) {
        this.imagensPexelService = imagensPexelService;
    }

    @GetMapping("/{nomeImagem}")
    public ResponseEntity<Pexels> get(@PathVariable String nomeImagem){

        log.info("Nome da img: {}", nomeImagem);

       return imagensPexelService.getImagemPexel(nomeImagem);
    }
}
