package pe.edu.upc.inkametrics_backend.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "detectionadvertising")
public class DetectionAdvertising {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetectionAdvertising;
    @Column(name = "typeTransmission", nullable = false)
    private String typeTransmission;
    @Column(name = "secondAppearanceTransmission", nullable = false)
    private int secondAppearanceTransmission;
    @Column(name = "durationsegTransmission", nullable = false)
    private int durationsegTransmission;
    @Column(name = "totalDuration", nullable = false)
    private Long totalDuration;

    public DetectionAdvertising() {
    }

    public DetectionAdvertising(int idDetectionAdvertising, String typeTransmission, int secondAppearanceTransmission, int durationsegTransmission, Long totalDuration) {
        this.idDetectionAdvertising = idDetectionAdvertising;
        this.typeTransmission = typeTransmission;
        this.secondAppearanceTransmission = secondAppearanceTransmission;
        this.durationsegTransmission = durationsegTransmission;
        this.totalDuration = totalDuration;
    }

    public int getIdDetectionAdvertising() {
        return idDetectionAdvertising;
    }

    public void setIdDetectionAdvertising(int idDetectionAdvertising) {
        this.idDetectionAdvertising = idDetectionAdvertising;
    }

    public String getTypeTransmission() {
        return typeTransmission;
    }

    public void setTypeTransmission(String typeTransmission) {
        this.typeTransmission = typeTransmission;
    }

    public int getSecondAppearanceTransmission() {
        return secondAppearanceTransmission;
    }

    public void setSecondAppearanceTransmission(int secondAppearanceTransmission) {
        this.secondAppearanceTransmission = secondAppearanceTransmission;
    }

    public int getDurationsegTransmission() {
        return durationsegTransmission;
    }

    public void setDurationsegTransmission(int durationsegTransmission) {
        this.durationsegTransmission = durationsegTransmission;
    }

    public Long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Long totalDuration) {
        this.totalDuration = totalDuration;
    }
}
