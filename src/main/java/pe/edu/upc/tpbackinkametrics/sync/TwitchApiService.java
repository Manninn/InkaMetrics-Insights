package pe.edu.upc.tpbackinkametrics.sync;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.edu.upc.tpbackinkametrics.sync.dto.TwitchStreamData;
import pe.edu.upc.tpbackinkametrics.sync.dto.TwitchTokenResponse;

import java.time.Instant;
import java.util.Optional;

@Service
public class TwitchApiService {

    @Value("${twitch.client-id}")
    private String clientId;

    @Value("${twitch.client-secret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String cachedToken;
    private Instant tokenExpiry = Instant.MIN;

    public boolean isConfigured() {
        return clientId != null && !clientId.isBlank()
            && clientSecret != null && !clientSecret.isBlank();
    }

    private String getAccessToken() {
        if (cachedToken != null && Instant.now().isBefore(tokenExpiry)) {
            return cachedToken;
        }
        String url = String.format(
            "https://id.twitch.tv/oauth2/token?client_id=%s&client_secret=%s&grant_type=client_credentials",
            clientId, clientSecret
        );
        ResponseEntity<TwitchTokenResponse> response =
            restTemplate.postForEntity(url, null, TwitchTokenResponse.class);

        TwitchTokenResponse body = response.getBody();
        if (body == null || body.getAccessToken() == null) {
            throw new RuntimeException("No se pudo obtener token de Twitch");
        }
        cachedToken = body.getAccessToken();
        tokenExpiry = Instant.now().plusSeconds(body.getExpiresIn() - 60);
        return cachedToken;
    }

    private HttpEntity<Void> authHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-Id", clientId);
        headers.set("Authorization", "Bearer " + getAccessToken());
        return new HttpEntity<>(headers);
    }

    public Optional<TwitchStreamData> getStream(String channelLogin) {
        if (!isConfigured()) return Optional.empty();
        try {
            String url = "https://api.twitch.tv/helix/streams?user_login=" + channelLogin;
            ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET, authHeaders(), String.class);

            JsonNode data = objectMapper.readTree(response.getBody()).get("data");
            if (data != null && data.isArray() && !data.isEmpty()) {
                return Optional.of(objectMapper.treeToValue(data.get(0), TwitchStreamData.class));
            }
        } catch (Exception e) {
            System.err.println("[Twitch] Error en " + channelLogin + ": " + e.getMessage());
        }
        return Optional.empty();
    }

    public int getFollowers(String channelLogin) {
        if (!isConfigured()) return 0;
        try {
            String userUrl = "https://api.twitch.tv/helix/users?login=" + channelLogin;
            ResponseEntity<String> userResp =
                restTemplate.exchange(userUrl, HttpMethod.GET, authHeaders(), String.class);
            JsonNode userData = objectMapper.readTree(userResp.getBody()).get("data");
            if (userData == null || !userData.isArray() || userData.isEmpty()) return 0;

            String userId = userData.get(0).get("id").asText();
            String followUrl = "https://api.twitch.tv/helix/channels/followers?broadcaster_id=" + userId;
            ResponseEntity<String> followResp =
                restTemplate.exchange(followUrl, HttpMethod.GET, authHeaders(), String.class);

            JsonNode total = objectMapper.readTree(followResp.getBody()).get("total");
            return total != null ? total.asInt() : 0;
        } catch (Exception e) {
            System.err.println("[Twitch] Error seguidores " + channelLogin + ": " + e.getMessage());
            return 0;
        }
    }
}
