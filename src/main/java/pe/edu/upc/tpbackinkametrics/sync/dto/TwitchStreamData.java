package pe.edu.upc.tpbackinkametrics.sync.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitchStreamData {

    @JsonProperty("user_login")
    private String userLogin;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("title")
    private String title;

    @JsonProperty("viewer_count")
    private int viewerCount;

    @JsonProperty("started_at")
    private String startedAt;

    @JsonProperty("language")
    private String language;

    @JsonProperty("game_name")
    private String gameName;

    public String getUserLogin() { return userLogin; }
    public String getUserName() { return userName; }
    public String getTitle() { return title; }
    public int getViewerCount() { return viewerCount; }
    public String getStartedAt() { return startedAt; }
    public String getLanguage() { return language; }
    public String getGameName() { return gameName; }
}
