package pe.edu.upc.tpbackinkametrics.entities;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "ad_detection")
public class AdDetection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", nullable = false, length = 100)
    private String type;

    @Column(name = "appearance_time", nullable = false)
    private LocalTime appearanceTime;

    @Column(name = "appearance_duration_sec", nullable = false)
    private int appearanceDurationSec;

    @ManyToOne
    @JoinColumn(name = "broadcast_id", nullable = false)
    private Broadcast broadcast;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    public AdDetection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalTime getAppearanceTime() {
        return appearanceTime;
    }

    public void setAppearanceTime(LocalTime appearanceTime) {
        this.appearanceTime = appearanceTime;
    }

    public int getAppearanceDurationSec() {
        return appearanceDurationSec;
    }

    public void setAppearanceDurationSec(int appearanceDurationSec) {
        this.appearanceDurationSec = appearanceDurationSec;
    }

    public Broadcast getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(Broadcast broadcast) {
        this.broadcast = broadcast;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
