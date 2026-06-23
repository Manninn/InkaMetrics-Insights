package pe.edu.upc.tpbackinkametrics.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pe.edu.upc.tpbackinkametrics.util.RapidApiHeaders;

@RestController
@RequestMapping("/youtube")
public class YoutubeProxyController {

    @Value("${rapidapi.key}")
    private String apiKey;

    @Value("${rapidapi.youtube.host}")
    private String host;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/channel/{id}")
    public ResponseEntity<String> getChannel(@PathVariable String id) {
        String url = "https://" + host + "/channels?part=snippet,statistics&id=" + id;
        return call(url);
    }

    @GetMapping("/channel/search")
    public ResponseEntity<String> searchChannel(@RequestParam String q) {
        String url = "https://" + host + "/search?part=snippet&type=channel&q=" + q;
        return call(url);
    }

    @GetMapping("/channel/{id}/live")
    public ResponseEntity<String> getLiveStream(@PathVariable String id) {
        String url = "https://" + host + "/search?part=snippet&channelId=" + id + "&eventType=live&type=video";
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
                .body("{\"error\":\"No se pudo conectar con YouTube\"}");
        }
    }
}
