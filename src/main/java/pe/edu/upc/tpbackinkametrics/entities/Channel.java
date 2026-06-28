package pe.edu.upc.tpbackinkametrics.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "channel")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "channel_url", nullable = false, length = 100)
    private String channelUrl;

    @Column(name = "current_followers", nullable = false)
    private int currentFollowers;

    @ManyToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @ManyToOne
    @JoinColumn(name = "streamer_id", nullable = true)
    private Streamer streamer;

    @JsonIgnore
    @OneToMany(mappedBy = "channel")
    private List<Broadcast> broadcasts;

    @JsonIgnore
    @OneToMany(mappedBy = "channel")
    private List<MonitoredChannel> monitoredChannels;

    public Channel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public int getCurrentFollowers() {
        return currentFollowers;
    }

    public void setCurrentFollowers(int currentFollowers) {
        this.currentFollowers = currentFollowers;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Streamer getStreamer() {
        return streamer;
    }

    public void setStreamer(Streamer streamer) {
        this.streamer = streamer;
    }
}
