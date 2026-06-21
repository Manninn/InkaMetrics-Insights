package pe.edu.upc.tpbackinkametrics.dtos;

import pe.edu.upc.tpbackinkametrics.entities.Transmision;

public class MetricaSnapshotDTO {
    private int idMetricaSnapshot;
    private String nombre;
    private int cantidad;
    private Transmision transmision;

    public int getIdMetricaSnapshot() { return idMetricaSnapshot; }
    public void setIdMetricaSnapshot(int idMetricaSnapshot) { this.idMetricaSnapshot = idMetricaSnapshot; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Transmision getTransmision() { return transmision; }
    public void setTransmision(Transmision transmision) { this.transmision = transmision; }
}
