package sptech.befitapi.application.response;

import lombok.Data;
import sptech.befitapi.resources.repository.entity.Dieta;
import sptech.befitapi.resources.repository.entity.IngredientesDieta;
import java.util.List;
import java.util.Optional;

@Data
public class DietaCompleta {
    private List<IngredientesDieta> ingredientes;

    public DietaCompleta(List<IngredientesDieta> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
