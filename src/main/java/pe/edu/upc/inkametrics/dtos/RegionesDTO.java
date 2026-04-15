package pe.edu.upc.inkametrics.dtos;

import jakarta.persistence.Column;

public class RegionesDTO {
    private int id_region;
    private String nombre;

    public int getId_region() {
        return id_region;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
