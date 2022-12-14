package sptech.befitapi.application.request;

import lombok.Data;
import sptech.befitapi.resources.repository.entity.Treino;
import sptech.befitapi.resources.repository.entity.Usuario;

import java.util.List;

@Data
public class TreinoRequest {
    private String nome;
    private String descricao;
    private String personId;
    private List<SerieRequest> series;

    public Treino toTreinoRepository(String imagem, Integer criadorId) {
        return new Treino(
                nome,
                descricao,
                imagem,
                new Usuario(criadorId)
        );
    }
}
