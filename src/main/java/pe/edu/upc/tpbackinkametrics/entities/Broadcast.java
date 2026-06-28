package pe.edu.upc.tpbackinkametrics.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "broadcast")
public class Broadcast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stream_title", nullable = false, length = 500)
    private String streamTitle;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    @Column(name = "is_live", nullable = false)
    private boolean isLive;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    @JsonIgnore
    @OneToMany(mappedBy = "broadcast")
    private List<MetricSnapshot> metricSnapshots;

    @JsonIgnore
    @OneToMany(mappedBy = "broadcast")
    private List<AdDetection> adDetections;

    public Broadcast() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreamTitle() {
        return streamTitle;
    }

    public void setStreamTitle(String streamTitle) {
        this.streamTitle = streamTitle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean getIsLive() {
        return isLive;
    }

    public void setIsLive(boolean isLive) {
        this.isLive = isLive;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
