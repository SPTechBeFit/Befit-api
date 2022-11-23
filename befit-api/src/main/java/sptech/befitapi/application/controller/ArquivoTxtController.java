package sptech.befitapi.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.befitapi.application.Util.ArquivoTxt;
import sptech.befitapi.application.response.DietaCompleta;
import sptech.befitapi.application.service.DietaService;
import sptech.befitapi.application.service.UsuarioService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/arquivotxt")
public class ArquivoTxtController {

    private final ArquivoTxt arquivoTxt;
    private final DietaService dietaService;
    private final UsuarioService usuarioService;

    public ArquivoTxtController(final ArquivoTxt arquivoTxt,
                                final DietaService dietaService,
                                final UsuarioService usuarioService) {

        this.arquivoTxt = arquivoTxt;
        this.dietaService = dietaService;
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/importar/dieta/{personId}", consumes = "text/*")
    public ResponseEntity<String> lerArquivoTxt(@PathVariable String personId, @RequestBody byte[] dieta) throws UnsupportedEncodingException {
        arquivoTxt.lerArquivoTxt(dieta,personId);
        return ResponseEntity.ok().body("Arquivo lido / Dieta gerada");
    }

    @GetMapping(value = "exportar/dieta/{idDieta}", produces = "text/plain")
    public ResponseEntity<Object> getArquivtoTxt(@PathVariable int idDieta) throws UnsupportedEncodingException {
        Optional<DietaCompleta> dieta = dietaService.getById(idDieta);
        if (dieta.get().getIngredientes().size() <= 0 ){
            return ResponseEntity.status(204).body(dieta);
        }

       byte[] ok = arquivoTxt.gravarDietaTxt(dieta, idDieta);
        return ResponseEntity.status(HttpStatus.OK).header("content-disposition", "attachment; filename=\"dieta-"+dieta.get().getDieta().get().getNome()+".txt\"").body(ok);
    }


}
