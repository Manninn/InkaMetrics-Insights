package pe.edu.upc.tpbackinkametrics.dtos;

import java.time.LocalTime;

public class AdDetectionSpecialDTO {
    private int id;
    private String type;
    private LocalTime appearanceTime;
    private int appearanceDurationSec;
    private int broadcastId;
    private int brandId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalTime getAppearanceTime() { return appearanceTime; }
    public void setAppearanceTime(LocalTime appearanceTime) { this.appearanceTime = appearanceTime; }

    public int getAppearanceDurationSec() { return appearanceDurationSec; }
    public void setAppearanceDurationSec(int appearanceDurationSec) { this.appearanceDurationSec = appearanceDurationSec; }

    public int getBroadcastId() { return broadcastId; }
    public void setBroadcastId(int broadcastId) { this.broadcastId = broadcastId; }

    public int getBrandId() { return brandId; }
    public void setBrandId(int brandId) { this.brandId = brandId; }
}
