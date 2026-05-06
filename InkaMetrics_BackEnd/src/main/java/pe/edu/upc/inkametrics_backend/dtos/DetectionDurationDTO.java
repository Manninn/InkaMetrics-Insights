package pe.edu.upc.inkametrics_backend.dtos;

public class DetectionDurationDTO {

    private String typeTransmission;
    private Long totalDuration;

    public String getTypeTransmission() {
        return typeTransmission;
    }

    public void setTypeTransmission(String typeTransmission) {
        this.typeTransmission = typeTransmission;
    }

    public Long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Long totalDuration) {
        this.totalDuration = totalDuration;
    }
}
