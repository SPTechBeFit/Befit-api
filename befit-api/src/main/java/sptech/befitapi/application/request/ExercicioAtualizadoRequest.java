package sptech.befitapi.application.request;

import lombok.Data;

@Data
public class ExercicioAtualizadoRequest {
    private String imagem;

    public ExercicioAtualizadoRequest(String imagem) {
        this.imagem = imagem;
    }

    public ExercicioAtualizadoRequest() {
    }
}
