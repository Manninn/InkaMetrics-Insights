package pe.edu.upc.tpbackinkametrics.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pe.edu.upc.tpbackinkametrics.util.RapidApiHeaders;

@RestController
@RequestMapping("/kick")
public class KickProxyController {

    @Value("${rapidapi.key}")
    private String apiKey;

    @Value("${rapidapi.kick.host}")
    private String host;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/channel/{slug}")
    public ResponseEntity<String> getChannel(@PathVariable String slug) {
        String url = "https://" + host + "/channels/" + slug;
        return call(url);
    }

    @GetMapping("/search")
    public ResponseEntity<String> search(@RequestParam String q) {
        String url = "https://" + host + "/search/channel?query=" + q;
        return call(url);
    }

    private ResponseEntity<String> call(String url) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET,
                new HttpEntity<>(RapidApiHeaders.build(apiKey, host)),
                String.class
            );
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("{\"error\":\"No se pudo conectar con Kick\"}");
        }
    }
}
