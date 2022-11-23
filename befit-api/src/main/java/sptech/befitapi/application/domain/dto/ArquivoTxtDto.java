package sptech.befitapi.application.domain.dto;

import lombok.Data;

@Data
public class ArquivoTxtDto {

    private Integer id;
    private String nomeDieta;
    private String nomeIngrediente;
    private Integer porcao;
    private Double proteina;
    private Double lipido;
    private Double carboidrato;
    private Double sodio;
    private Double caloria;

    public ArquivoTxtDto(Integer id, String nomeDieta, String nomeIngrediente, Integer porcao, Double proteina,
                         Double lipido, Double carboidrato, Double sodio, Double caloria) {
        this.id = id;
        this.nomeDieta = nomeDieta;
        this.nomeIngrediente = nomeIngrediente;
        this.porcao = porcao;
        this.proteina = proteina;
        this.lipido = lipido;
        this.carboidrato = carboidrato;
        this.sodio = sodio;
        this.caloria = caloria;
    }
}
