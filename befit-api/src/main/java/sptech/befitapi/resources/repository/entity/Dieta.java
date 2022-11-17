package sptech.befitapi.resources.repository.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descricao;
    private String imagem;

    @ManyToOne
    private Usuario criador;

    public Dieta(Integer id) {
        this.id = id;
    }

    public Dieta(Integer id, String nome, String descricao, String imagem, Usuario criador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.criador = criador;
    }

    public Dieta() {
    }
}
