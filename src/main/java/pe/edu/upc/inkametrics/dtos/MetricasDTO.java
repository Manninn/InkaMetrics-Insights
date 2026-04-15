package pe.edu.upc.inkametrics.dtos;

import jakarta.persistence.Column;

public class MetricasDTO {
    private int id;
    private String nameMetrica;
    private int cantidadMetrica;

    public int getId() {
        return id;
    }

    public String getNameMetrica() {
        return nameMetrica;
    }

    public int getCantidadMetrica() {
        return cantidadMetrica;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameMetrica(String nameMetrica) {
        this.nameMetrica = nameMetrica;
    }

    public void setCantidadMetrica(int cantidadMetrica) {
        this.cantidadMetrica = cantidadMetrica;
    }
}
