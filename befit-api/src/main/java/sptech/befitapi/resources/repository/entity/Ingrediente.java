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
}
