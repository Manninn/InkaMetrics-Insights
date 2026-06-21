package pe.edu.upc.tpbackinkametrics.sync;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.edu.upc.tpbackinkametrics.sync.dto.KickChannelData;

import java.util.Optional;

@Service
public class KickApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Optional<KickChannelData> getChannel(String channelSlug) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
            headers.set("Accept", "application/json");

            String url = "https://kick.com/api/v2/channels/" + channelSlug;
            ResponseEntity<KickChannelData> response = restTemplate.exchange(
                url, HttpMethod.GET, new HttpEntity<>(headers), KickChannelData.class
            );
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            System.err.println("[Kick] Error en " + channelSlug + ": " + e.getMessage());
            return Optional.empty();
        }
    }
}
