package pe.edu.upc.tpbackinkametrics.dtos;

public class MetricsByBroadcastDTO {

    private String streamTitle;
    private String metricName;
    private int totalAmount;

    public MetricsByBroadcastDTO() {
    }

    public MetricsByBroadcastDTO(String streamTitle, String metricName, int totalAmount) {
        this.streamTitle = streamTitle;
        this.metricName = metricName;
        this.totalAmount = totalAmount;
    }

    public String getStreamTitle() {
        return streamTitle;
    }

    public void setStreamTitle(String streamTitle) {
        this.streamTitle = streamTitle;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
