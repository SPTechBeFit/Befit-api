package sptech.befitapi.resources.repository.entity;

import lombok.Data;
import sptech.befitapi.resources.repository.entity.types.NivelType;
import javax.persistence.*;

@Data
@Entity
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descricao;

    @ManyToOne
    private Usuario criador;
}
