package pe.edu.upc.inkametrics.dtos;

import jakarta.persistence.Column;

public class RolesDTO {
    private int id_role;
    private String nombre;

    public int getId_role() {
        return id_role;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
