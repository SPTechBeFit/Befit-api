package sptech.befitapi.resources.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Data
@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Treino treino;

    @ManyToOne
    private Exercicio exercicio;

    private Integer quantidade = 0;

    private LocalTime tempo = LocalTime.of(0,0,0);

    private Integer repeticao = 0;

    public Serie(Treino treino, Exercicio exercicio, Integer quantidade, LocalTime tempo, Integer repeticao) {
        this.treino = treino;
        this.exercicio = exercicio;
        this.quantidade = quantidade;
        this.tempo = tempo;
        this.repeticao = repeticao;
    }

    public Serie() {
    }
}
