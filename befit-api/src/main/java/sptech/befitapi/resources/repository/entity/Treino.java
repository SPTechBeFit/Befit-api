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

    public Treino(Integer id, String nome, String descricao, Usuario criador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.criador = criador;
    }

    public Treino(String nome, String descricao, Usuario criador) {
        this.nome = nome;
        this.descricao = descricao;
        this.criador = criador;
    }

    public Treino(Integer id) {
        this.id = id;
    }
}
