package sptech.befitapi.application.response;

import lombok.Data;
import sptech.befitapi.resources.repository.entity.TreinoFavorito;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreinoFavoritoResponse {
    private Integer id;
    private String nome;
    private String descricao;
    private String imagem;

    private Boolean favoritado;

    public TreinoFavoritoResponse(Integer id, String nome, String descricao, String imagem, Boolean favoritado) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.favoritado = favoritado;
    }

    public TreinoFavoritoResponse() {
    }

    public List<TreinoFavoritoResponse> fromTreinoFavoritoRepository(List<TreinoFavorito> treinos) {
        List<TreinoFavoritoResponse> response = new ArrayList<>();
        for (TreinoFavorito t : treinos) {
            response.add(new TreinoFavoritoResponse(
                    t.getTreino().getId(),
                    t.getTreino().getNome(),
                    t.getTreino().getDescricao(),
                    t.getTreino().getImagem(),
                    true
                )
            );
        }

        return response;
    }
}
