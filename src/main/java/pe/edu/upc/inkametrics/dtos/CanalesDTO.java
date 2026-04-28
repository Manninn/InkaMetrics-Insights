package pe.edu.upc.inkametrics.dtos;

import jakarta.persistence.Column;

public class CanalesDTO {
    private int id_canal;
    private String url_canal;
    private int seguidores_actuales;
    private int id_plataforma;

    public int getId_canal() {
        return id_canal;
    }

    public String getUrl_canal() {
        return url_canal;
    }

    public int getSeguidores_actuales() {
        return seguidores_actuales;
    }

    public void setId_canal(int id_canal) {
        this.id_canal = id_canal;
    }

    public void setUrl_canal(String url_canal) {
        this.url_canal = url_canal;
    }

    public void setSeguidores_actuales(int seguidores_actuales) {
        this.seguidores_actuales = seguidores_actuales;
    }

    public int getId_plataforma() {
        return id_plataforma;
    }

    public void setId_plataforma(int id_plataforma) {this.id_plataforma = id_plataforma;}
}
