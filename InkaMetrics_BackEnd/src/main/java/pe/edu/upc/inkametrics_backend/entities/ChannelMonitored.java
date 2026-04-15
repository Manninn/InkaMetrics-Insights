package pe.edu.upc.inkametrics_backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "channelmonitored")
public class ChannelMonitored {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idChannelMonitored;

    public ChannelMonitored() {
    }

    public ChannelMonitored(int idChannelMonitored) {
        this.idChannelMonitored = idChannelMonitored;
    }

    public int getIdChannelMonitored() {
        return idChannelMonitored;
    }

    public void setIdChannelMonitored(int idChannelMonitored) {
        this.idChannelMonitored = idChannelMonitored;
    }
}
