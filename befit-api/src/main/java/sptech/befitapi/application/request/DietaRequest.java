package sptech.befitapi.application.request;

import lombok.Data;
import sptech.befitapi.resources.repository.entity.Dieta;
import sptech.befitapi.resources.repository.entity.IngredientesDieta;

import java.util.List;

@Data
public class DietaRequest {
    private Dieta dieta;
    private String personId;
    private List<IngredientesDieta> ingredientesDieta;

    public DietaRequest() {
    }

    public DietaRequest(Dieta dieta, String personId, List<IngredientesDieta> ingredientesDieta) {
        this.dieta = dieta;
        this.personId = personId;
        this.ingredientesDieta = ingredientesDieta;
    }
}
