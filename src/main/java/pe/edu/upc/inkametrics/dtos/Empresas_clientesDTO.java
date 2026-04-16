package pe.edu.upc.inkametrics.dtos;

import jakarta.persistence.Column;

public class Empresas_clientesDTO {
    private int id_empresa;
    private String nombre_comercial;
    private String ruc;

    public int getId_empresa() {
        return id_empresa;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
}
