package pe.edu.upc.inkametrics.dtos;

import jakarta.persistence.Column;

public class Detecciones_publicitariasDTO {
    private int id;
    private String tipo;
    private int segundo_aparicion;
    private int duracion_seg;

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getSegundo_aparicion() {
        return segundo_aparicion;
    }

    public int getDuracion_seg() {
        return duracion_seg;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setSegundo_aparicion(int segundo_aparicion) {
        this.segundo_aparicion = segundo_aparicion;
    }

    public void setDuracion_seg(int duracion_seg) {
        this.duracion_seg = duracion_seg;
    }
}
