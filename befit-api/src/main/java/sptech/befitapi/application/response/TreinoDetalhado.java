package sptech.befitapi.application.response;

import lombok.Data;
import sptech.befitapi.resources.repository.entity.Serie;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TreinoDetalhado {
    private int idExercicio;
    private String nome;
    private String nomeExercicio;
    private String descricao;
    private String imagem;
    private Integer quantidade;
    private LocalTime tempo;
    private Integer repeticao;

    public TreinoDetalhado() {
    }

    public TreinoDetalhado(int idExercicio, String nome, String descricao, String imagem, Integer quantidade, LocalTime tempo, Integer repeticao) {
        this.idExercicio = idExercicio;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.quantidade = quantidade;
        this.tempo = tempo;
        this.repeticao = repeticao;
    }

    public List<TreinoDetalhado> fromSerieRepository(List<Serie> series) {
        List<TreinoDetalhado> exerciciosTreino = new ArrayList<>();
        for (Serie s: series) {
            exerciciosTreino.add(new TreinoDetalhado(
                s.getExercicio().getId(),
                s.getExercicio().getNome(),
                s.getExercicio().getDescricao(),
                s.getExercicio().getImagem(),
                s.getQuantidade(),
                s.getTempo(),
                s.getRepeticao()
                )
            );
        }
        return exerciciosTreino;
    }
}
