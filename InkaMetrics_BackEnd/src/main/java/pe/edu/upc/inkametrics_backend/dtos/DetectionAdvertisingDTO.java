package pe.edu.upc.inkametrics_backend.dtos;

public class DetectionAdvertisingDTO {
    private int idDetectionAdvertising;
    private String typeTransmission;
    private int secondAppearanceTransmission;
    private int durationsegTransmission;

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
}
