package pe.edu.upc.inkametrics.dtos;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class StreamersDTO {
    private int id_streamer;
    private String nickname;
    private String genero;
    private LocalDate fecha_registro_app;

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
}
