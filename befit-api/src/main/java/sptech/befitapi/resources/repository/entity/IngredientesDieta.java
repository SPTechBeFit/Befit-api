package sptech.befitapi.resources.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class IngredientesDieta {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Dieta dieta;

    @ManyToOne
    private Ingrediente ingrediente;

    private Double quantidade;

}
