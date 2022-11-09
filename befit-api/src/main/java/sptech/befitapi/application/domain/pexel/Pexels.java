package sptech.befitapi.application.domain.pexel;

import lombok.Data;

import java.util.List;

@Data
public class Pexels {
    private Integer page;
    private Integer per_page;
    private List<Photo> photos;
    private Integer total_results;
    private String next_page;
}
