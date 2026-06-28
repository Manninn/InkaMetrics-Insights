package pe.edu.upc.tpbackinkametrics.dtos;

public class MonitoredChannelDTO {
    private int id;
    private int channelId;
    private Integer companyId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getChannelId() { return channelId; }
    public void setChannelId(int channelId) { this.channelId = channelId; }

    public Integer getCompanyId() { return companyId; }
    public void setCompanyId(Integer companyId) { this.companyId = companyId; }
}
