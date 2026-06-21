package pe.edu.upc.tpbackinkametrics.sync.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KickChannelData {

    @JsonProperty("followers_count")
    private int followersCount;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("livestream")
    private KickLivestream livestream;

    public int getFollowersCount() { return followersCount; }
    public String getSlug() { return slug; }
    public KickLivestream getLivestream() { return livestream; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KickLivestream {

        @JsonProperty("session_title")
        private String sessionTitle;

        @JsonProperty("viewers")
        private int viewers;

        @JsonProperty("created_at")
        private String createdAt;

        public String getSessionTitle() { return sessionTitle; }
        public int getViewers() { return viewers; }
        public String getCreatedAt() { return createdAt; }
    }
}
