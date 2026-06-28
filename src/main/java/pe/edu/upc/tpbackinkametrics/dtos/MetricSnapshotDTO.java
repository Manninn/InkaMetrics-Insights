package pe.edu.upc.tpbackinkametrics.dtos;

public class MetricSnapshotDTO {
    private int id;
    private String name;
    private int amount;
    private int broadcastId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public int getBroadcastId() { return broadcastId; }
    public void setBroadcastId(int broadcastId) { this.broadcastId = broadcastId; }
}
