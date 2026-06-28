package pe.edu.upc.tpbackinkametrics.dtos;

import java.time.LocalDate;

public class BroadcastDTO {
    private int id;
    private String streamTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isLive;
    private int channelId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStreamTitle() { return streamTitle; }
    public void setStreamTitle(String streamTitle) { this.streamTitle = streamTitle; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public boolean getIsLive() { return isLive; }
    public void setIsLive(boolean isLive) { this.isLive = isLive; }

    public int getChannelId() { return channelId; }
    public void setChannelId(int channelId) { this.channelId = channelId; }
}
