package sptech.befitapi.application.domain.pexel;

import lombok.Data;

@Data
public class Photo {
    private Integer id;
    private Integer width;
    private Integer height;
    private String url;
    private String photographer;
    private String photographer_url;
    private Integer photographer_id;
    private String avg_color;
    private Src src;
    private String alt;
}
