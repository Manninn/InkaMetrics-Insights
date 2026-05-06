package upc.edu.pe.tpbackinkametrics.dtos;

import jakarta.persistence.Column;

public class RegionDTO {
    private int IdRegion;
    private String Nombre;

    public int getIdRegion() {
        return IdRegion;
    }

    public void setIdRegion(int idRegion) {
        IdRegion = idRegion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

}
