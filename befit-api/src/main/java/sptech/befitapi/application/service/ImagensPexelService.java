package sptech.befitapi.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sptech.befitapi.application.domain.pexel.Pexels;

import java.net.URI;

@Service
@Slf4j
public class ImagensPexelService {

    public URI getUrlPexels(String nomeDaImagem){

        String n = "?";
            URI uri = UriComponentsBuilder.fromUriString("https://api.pexels.com")
                    .pathSegment("v1")
                    .pathSegment("search")
                    .queryParam("query="+nomeDaImagem)
                    .queryParam("per_page=1")
                    .queryParam("locale=pt-BR")
                    .build().toUri();
            log.info("URI{}", uri + "?");
            return uri;
    }

    public ResponseEntity<Pexels> getImagemPexel(String nomeDaImagem) {
        RestTemplate restTemplate = new RestTemplate();
        final var httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
        httpHeaders.setBearerAuth("563492ad6f91700001000001ed36b8f7a65d4ae7ac1d05663cf22ddb");
        Pexels pexels = new Pexels();
        final var entity = new HttpEntity<>(pexels, httpHeaders);
        return restTemplate.exchange(this.getUrlPexels(nomeDaImagem), HttpMethod.GET,entity ,Pexels.class);
    }
}
