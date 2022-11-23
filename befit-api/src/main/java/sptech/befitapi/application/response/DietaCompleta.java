package sptech.befitapi.application.response;

import lombok.Data;
import sptech.befitapi.resources.repository.entity.Dieta;
import sptech.befitapi.resources.repository.entity.Ingrediente;
import sptech.befitapi.resources.repository.entity.IngredientesDieta;
import java.util.List;
import java.util.Optional;

@Data
public class DietaCompleta {

    private Dieta dieta;

    private List<Ingrediente> ingredientes;

    public DietaCompleta(Dieta dieta, List<Ingrediente> ingredientes) {
        this.dieta = dieta;
        this.ingredientes = ingredientes;
    }
}
