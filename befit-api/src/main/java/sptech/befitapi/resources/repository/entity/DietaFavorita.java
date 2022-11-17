package sptech.befitapi.resources.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class DietaFavorita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Dieta dieta;

    public DietaFavorita(Usuario usuario, Dieta dieta) {
        this.usuario = usuario;
        this.dieta = dieta;
    }

    public DietaFavorita(Integer id, Usuario usuario, Dieta dieta) {
        this.id = id;
        this.usuario = usuario;
        this.dieta = dieta;
    }

    public DietaFavorita() {
    }
}
