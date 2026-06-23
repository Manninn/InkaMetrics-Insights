package pe.edu.upc.tpbackinkametrics.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "streamer")
public class Streamer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdStreamer;

    @Column(name = "NickName", nullable = false, length = 100)
    private String Nickname;

    @Column(name = "Genero", nullable = false, length = 100)
    private String Genero;

    @Column(name = "FechaRegistroApp", nullable = false)
    private LocalDate FechaRegistroApp;

    @ManyToOne
    @JoinColumn(name = "IdRegion", nullable = false)
    private Region region;

    @JsonIgnore
    @OneToMany(mappedBy = "Streamer", cascade = CascadeType.REMOVE)
    private List<Canal> canales;

    public Streamer() {
    }

    public int getIdStreamer() {
        return IdStreamer;
    }

    public void setIdStreamer(int idStreamer) {
        IdStreamer = idStreamer;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public LocalDate getFechaRegistroApp() {
        return FechaRegistroApp;
    }

    public void setFechaRegistroApp(LocalDate fechaRegistroApp) {
        FechaRegistroApp = fechaRegistroApp;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
