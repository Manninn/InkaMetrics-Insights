package pe.edu.upc.tpbackinkametrics.dtos;

public class ChannelDTO {
    private int id;
    private String channelUrl;
    private int currentFollowers;
    private int platformId;
    private Integer streamerId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getChannelUrl() { return channelUrl; }
    public void setChannelUrl(String channelUrl) { this.channelUrl = channelUrl; }

    public int getCurrentFollowers() { return currentFollowers; }
    public void setCurrentFollowers(int currentFollowers) { this.currentFollowers = currentFollowers; }

    public int getPlatformId() { return platformId; }
    public void setPlatformId(int platformId) { this.platformId = platformId; }

    public Integer getStreamerId() { return streamerId; }
    public void setStreamerId(Integer streamerId) { this.streamerId = streamerId; }
}
