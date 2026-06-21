package pe.edu.upc.tpbackinkametrics.sync.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitchTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private long expiresIn;

    public String getAccessToken() { return accessToken; }
    public long getExpiresIn() { return expiresIn; }
}
