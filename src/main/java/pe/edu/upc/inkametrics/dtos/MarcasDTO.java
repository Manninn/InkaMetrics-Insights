package pe.edu.upc.inkametrics.dtos;

import jakarta.persistence.Column;

public class MarcasDTO {
    private int id_marca;
    private String nombre;
    private String sector;

    public int getId_marca() {
        return id_marca;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSector() {
        return sector;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
