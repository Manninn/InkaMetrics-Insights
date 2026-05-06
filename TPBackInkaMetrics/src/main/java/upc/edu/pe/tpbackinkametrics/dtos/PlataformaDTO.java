package upc.edu.pe.tpbackinkametrics.dtos;

import jakarta.persistence.Column;

public class PlataformaDTO {
    private int IdPlataforma;
    private String Nombre;
    private String urlBase;

    public int getIdPlataforma() {
        return IdPlataforma;
    }

    public void setIdPlataforma(int idPlataforma) {
        this.IdPlataforma = idPlataforma;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

}
