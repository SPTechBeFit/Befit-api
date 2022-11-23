package sptech.befitapi.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.befitapi.application.Util.ArquivoTxt;
import sptech.befitapi.application.response.DietaCompleta;
import sptech.befitapi.application.service.DietaService;
import sptech.befitapi.application.service.UsuarioService;

import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/arquivotxt")
public class ArquivoTxtController {

    @Autowired
    private ArquivoTxt arquivoTxt;

    @Autowired
    private DietaService dietaService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/importar/dieta/{personId}", consumes = "text/*")
    public ResponseEntity<String> lerArquivoTxt(@PathVariable String personId, @RequestBody byte[] dieta) throws UnsupportedEncodingException {
        arquivoTxt.agendarExecucao(personId, dieta);
        return ResponseEntity.ok().body("Arquivo lido / Dieta gerada");
    }

    @GetMapping(value = "/exportar/dieta/{idDieta}", produces = "text/plain")
    public ResponseEntity<Object> getArquivtoTxt(@PathVariable int idDieta) throws UnsupportedEncodingException {
        DietaCompleta dieta = dietaService.getById(idDieta);
        if (dieta.getIngredientes().size() <= 0 ){
            return ResponseEntity.status(204).body(dieta);
        }

       byte[] ok = arquivoTxt.gravarDietaTxt(dieta, idDieta);
        return ResponseEntity.status(HttpStatus.OK).header("content-disposition", "attachment; filename=\"dieta-"+dieta.getIngredientes().get(0).getDieta().getNome()+".txt\"").body(ok);
    }


}
