package pe.edu.upc.inkametrics.dtos;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class TransmisionesDTO {
    private int id_transmision;
    private String titulo_stream;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private boolean en_vivo;

    public int getId_transmision() {
        return id_transmision;
    }

    public String getTitulo_stream() {
        return titulo_stream;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public boolean isEn_vivo() {
        return en_vivo;
    }

    public void setId_transmision(int id_transmision) {
        this.id_transmision = id_transmision;
    }

    public void setTitulo_stream(String titulo_stream) {
        this.titulo_stream = titulo_stream;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public void setEn_vivo(boolean en_vivo) {
        this.en_vivo = en_vivo;
    }
}
