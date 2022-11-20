package sptech.befitapi.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.befitapi.application.Util.ArquivoTxt;
import sptech.befitapi.application.response.DietaCompleta;
import sptech.befitapi.application.service.DietaService;
import sptech.befitapi.resources.repository.UsuarioRepository;
import sptech.befitapi.resources.repository.entity.Usuario;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/arquivotxt")
public class ArquivoTxtController {

    private final ArquivoTxt arquivoTxt;
    private final DietaService dietaService;
    private final UsuarioRepository usuarioRepository;

    public ArquivoTxtController(final ArquivoTxt arquivoTxt,
                                final DietaService dietaService,
                                final UsuarioRepository usuarioRepository) {

        this.arquivoTxt = arquivoTxt;
        this.dietaService = dietaService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/importar/dieta/{personId}")
    public ResponseEntity<String> lerArquivoTxt(@PathVariable String personId){
       Usuario usuario =  usuarioRepository.findByPersonId(personId);
       if (usuario == null){
           return  ResponseEntity.status(404).body("Usuario não encontrado");
       }
        arquivoTxt.lerArquivoTxt("Dieta",personId);
        return ResponseEntity.ok().body("Arquivo lido / Dieta gerada");
    }

    @GetMapping("exportar/dieta/{idDieta}")
    public ResponseEntity<Object> getArquivtoTxt(@PathVariable int idDieta) {
        Optional<DietaCompleta> dieta = dietaService.getById(idDieta);
        if (dieta.get().getIngredientes().size() <= 0 ){
            return ResponseEntity.status(204).body(dieta);
        }
        arquivoTxt.agendarExecucao(dieta);
        return ResponseEntity.status(HttpStatus.OK).body("Sua dieta será exportada 15S");
    }


}
