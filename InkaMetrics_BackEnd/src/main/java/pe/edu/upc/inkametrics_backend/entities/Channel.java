package pe.edu.upc.inkametrics_backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "channel")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idChannel;

    @Column(name = "urlChannel",nullable = false)
    private String urlChannel;

    @Column(name = "actualFollowers",nullable = false)
    private int actualFollowers;

    public Channel() {
    }

    public Channel(int idChannel, String urlChannel, int actualFollowers) {
        this.idChannel = idChannel;
        this.urlChannel = urlChannel;
        this.actualFollowers = actualFollowers;
    }

    public int getIdChannel() {
        return idChannel;
    }

    public void setIdChannel(int idChannel) {
        this.idChannel = idChannel;
    }

    public String getUrlChannel() {
        return urlChannel;
    }

    public void setUrlChannel(String urlChannel) {
        this.urlChannel = urlChannel;
    }

    public int getActualFollowers() {
        return actualFollowers;
    }

    public void setActualFollowers(int actualFollowers) {
        this.actualFollowers = actualFollowers;
    }
}
