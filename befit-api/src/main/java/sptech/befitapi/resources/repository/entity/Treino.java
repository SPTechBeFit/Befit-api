package sptech.befitapi.resources.repository.entity;

import lombok.Data;
import sptech.befitapi.resources.repository.entity.types.NivelType;

import javax.persistence.*;

@Data
@Entity
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descricao;

    private String imagem;

    @ManyToOne
    private Usuario criador;

    public Treino(Integer id, String nome, String descricao, String imagem, Usuario criador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.criador = criador;
    }

    public Treino() {
    }

    public Treino(String nome, String descricao, String imagem, Usuario criador) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.criador = criador;
    }

    public Treino(Integer id) {
        this.id = id;
    }
}
