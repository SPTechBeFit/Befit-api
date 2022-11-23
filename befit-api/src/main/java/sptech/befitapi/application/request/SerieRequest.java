package sptech.befitapi.application.request;

import lombok.Data;
import sptech.befitapi.resources.repository.entity.Exercicio;
import sptech.befitapi.resources.repository.entity.Serie;
import sptech.befitapi.resources.repository.entity.Treino;
import java.time.LocalTime;

@Data
public class SerieRequest {
    private Integer exercicioId;
    private Integer quantidade = 0;
    private Integer repeticao = 0;
    private LocalTime tempo = LocalTime.of(0,0,0);

    public SerieRequest(Integer exercicioId, Integer quantidade, Integer repeticao, LocalTime tempo) {
        this.exercicioId = exercicioId;
        this.quantidade = quantidade;
        this.repeticao = repeticao;
        this.tempo = tempo;
    }

    public Serie toSerieRepository(Integer treinoId) {
        return new Serie(
                new Treino(treinoId),
                new Exercicio(exercicioId),
                quantidade,
                tempo,
                repeticao
        );
    }

    public SerieRequest() {
    }
}
