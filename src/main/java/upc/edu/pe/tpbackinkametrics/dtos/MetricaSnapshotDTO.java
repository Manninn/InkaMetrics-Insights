package upc.edu.pe.tpbackinkametrics.dtos;


import upc.edu.pe.tpbackinkametrics.entities.Transmision;

public class MetricaSnapshotDTO {
    private int IdMetricaSnapshot;
    private String Nombre;
    private int Cantidad;
    private Transmision Transmision;

    public int getIdMetricaSnapshot() {
        return IdMetricaSnapshot;
    }

    public void setIdMetricaSnapshot(int idMetricaSnapshot) {
        IdMetricaSnapshot = idMetricaSnapshot;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public Transmision getTransmision() {
        return Transmision;
    }

    public void setTransmision(Transmision transmision) {
        Transmision = transmision;
    }
}
