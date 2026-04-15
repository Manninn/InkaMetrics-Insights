package pe.edu.upc.inkametrics_backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "metricsnapshot")
public class MetricSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idMetricSnapshot;
    @Column(name = "nameMetric",nullable = false)
    private String nameMetric;
    @Column(name = "quantityMetrics",nullable = false)
    private int quantityMetrics;

    public MetricSnapshot() {
    }

    public MetricSnapshot(int idMetricSnapshot, String nameMetric, int quantityMetrics) {
        this.idMetricSnapshot = idMetricSnapshot;
        this.nameMetric = nameMetric;
        this.quantityMetrics = quantityMetrics;
    }

    public int getIdMetricSnapshot() {
        return idMetricSnapshot;
    }

    public void setIdMetricSnapshot(int idMetricSnapshot) {
        this.idMetricSnapshot = idMetricSnapshot;
    }

    public String getNameMetric() {
        return nameMetric;
    }

    public void setNameMetric(String nameMetric) {
        this.nameMetric = nameMetric;
    }

    public int getQuantityMetrics() {
        return quantityMetrics;
    }

    public void setQuantityMetrics(int quantityMetrics) {
        this.quantityMetrics = quantityMetrics;
    }
}
