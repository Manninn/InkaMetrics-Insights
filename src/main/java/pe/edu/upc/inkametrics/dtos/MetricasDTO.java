package pe.edu.upc.inkametrics.dtos;


public class MetricasDTO {
    private int id;
    private String nameMetrica;
    private int cantidadMetrica;

    public int getIdMetricas() {
        return id;
    }

    public String getNameMetrica() {
        return nameMetrica;
    }

    public int getCantidadMetrica() {
        return cantidadMetrica;
    }

    public void setIdMetricas(int id) {
        this.id = id;
    }

    public void setNameMetrica(String nameMetrica) {
        this.nameMetrica = nameMetrica;
    }

    public void setCantidadMetrica(int cantidadMetrica) {
        this.cantidadMetrica = cantidadMetrica;
    }
}
