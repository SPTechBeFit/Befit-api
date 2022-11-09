package sptech.befitapi.application.request;

import lombok.Data;
import sptech.befitapi.resources.repository.entity.Exercicio;

@Data
public class ExercicioRequest {
    private String nome;
    private String descricao;

    public Exercicio toRepository(String imagem) {
        return new Exercicio(
               nome,
               descricao,
               imagem
        );
    }
}
