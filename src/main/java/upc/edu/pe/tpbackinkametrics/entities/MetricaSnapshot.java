package upc.edu.pe.tpbackinkametrics.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "metricaSnapshot")
public class MetricaSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdMetricaSnapshot;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String Nombre;

    @Column(name = "Cantidad", nullable = false)
    private int Cantidad;

    @ManyToOne
    @JoinColumn(name = "IdTransmision", nullable = false)
    private Transmision Transmision;

    public MetricaSnapshot() {
    }

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
