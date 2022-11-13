package sptech.befitapi.application.response;

import lombok.Data;

@Data
public class CatalogoTreinoResponse {
    private Integer id;
    private String nome;
    private String descricao;
    private String imagem;
    private Boolean favoritado;

    public CatalogoTreinoResponse() {
    }

    public CatalogoTreinoResponse(Integer id, String nome, String descricao, String imagem, Boolean favoritado) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.favoritado = favoritado;
    }
}
