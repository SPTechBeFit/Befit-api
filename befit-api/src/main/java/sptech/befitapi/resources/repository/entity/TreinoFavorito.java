package sptech.befitapi.resources.repository.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class TreinoFavorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Treino treino;

    public TreinoFavorito() {
    }

    public TreinoFavorito(Integer id, Usuario usuario, Treino treino) {
        this.id = id;
        this.usuario = usuario;
        this.treino = treino;
    }

    public TreinoFavorito(Usuario usuario, Treino treino) {
        this.usuario = usuario;
        this.treino = treino;
    }
}
