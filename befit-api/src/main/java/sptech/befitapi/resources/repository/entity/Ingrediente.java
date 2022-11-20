package sptech.befitapi.resources.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private Integer porcao;
    private Double proteina;
    private Double lipidio;
    private Double carboidrato;
    private Double sodio;
    private Double caloria;

    public Ingrediente(String nome, Integer porcao, Double proteina, Double lipidio, Double carboidrato, Double sodio, Double caloria) {
        this.nome = nome;
        this.porcao = porcao;
        this.proteina = proteina;
        this.lipidio = lipidio;
        this.carboidrato = carboidrato;
        this.sodio = sodio;
        this.caloria = caloria;
    }

    public Ingrediente() {
    }
}
