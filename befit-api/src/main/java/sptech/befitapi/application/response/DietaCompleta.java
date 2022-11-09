package sptech.befitapi.application.response;

import lombok.Data;
import sptech.befitapi.resources.repository.entity.Dieta;
import sptech.befitapi.resources.repository.entity.IngredientesDieta;
import java.util.List;
import java.util.Optional;

@Data
public class DietaCompleta {
    private Optional<Dieta> dieta;
    private List<IngredientesDieta> ingredientes;

    public DietaCompleta(Optional<Dieta> dieta, List<IngredientesDieta> ingredientes) {
        this.dieta = dieta;
        this.ingredientes = ingredientes;
    }
}
