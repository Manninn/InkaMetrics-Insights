package pe.edu.upc.inkametrics.entities;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Streamers")

public class Streamers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_streamer;
    @Column(name = "nickname", length = 100, nullable = false)
    private String nickname;
    @Column(name = "genero", length = 100)
    private String genero;
    @Column(name = "fecha_registro_app")
    private LocalDate fecha_registro_app;

    @ManyToOne
    @JoinColumn(name = "id_region")
    private Regiones regiones;

    public Streamers(int id_streamer, String nickname, String genero, LocalDate fecha_registro_app, Regiones regiones) {
        this.id_streamer = id_streamer;
        this.nickname = nickname;
        this.genero = genero;
        this.fecha_registro_app = fecha_registro_app;
        this.regiones = regiones;
    }

    public Streamers() {

    }

    public int getId_streamer() {
        return id_streamer;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGenero() {
        return genero;
    }

    public LocalDate getFecha_registro_app() {
        return fecha_registro_app;
    }

    public Regiones getRegiones() {
        return regiones;
    }

    public void setId_streamer(int id_streamer) {
        this.id_streamer = id_streamer;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setFecha_registro_app(LocalDate fecha_registro_app) {
        this.fecha_registro_app = fecha_registro_app;
    }

    public void setRegiones(Regiones regiones) {
        this.regiones = regiones;
    }
}
