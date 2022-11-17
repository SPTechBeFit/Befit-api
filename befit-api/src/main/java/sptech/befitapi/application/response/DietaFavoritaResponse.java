package sptech.befitapi.application.response;

import lombok.Data;
import sptech.befitapi.resources.repository.entity.DietaFavorita;
import sptech.befitapi.resources.repository.entity.TreinoFavorito;

import java.util.ArrayList;
import java.util.List;

@Data
public class DietaFavoritaResponse {
    private Integer id;
    private String nome;
    private String descricao;
    private String imagem;
    private Boolean favoritado;

    public DietaFavoritaResponse(Integer id, String nome, String descricao, String imagem, Boolean favoritado) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.favoritado = favoritado;
    }

    public DietaFavoritaResponse() {
    }

    public List<DietaFavoritaResponse> fromDietaFavoritaRepository(List<DietaFavorita> dietas) {
        List<DietaFavoritaResponse> response = new ArrayList<>();
        for (DietaFavorita d : dietas) {
            response.add(new DietaFavoritaResponse(
                            d.getDieta().getId(),
                            d.getDieta().getNome(),
                            d.getDieta().getDescricao(),
                            d.getDieta().getImagem(),
                            true
                    )
            );
        }

        return response;
    }
}
