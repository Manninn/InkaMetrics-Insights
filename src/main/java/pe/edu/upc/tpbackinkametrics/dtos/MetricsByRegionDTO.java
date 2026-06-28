package pe.edu.upc.tpbackinkametrics.dtos;

public class MetricsByRegionDTO {

    private String regionName;
    private String metricName;
    private double averageAmount;

    public MetricsByRegionDTO() {}

    public MetricsByRegionDTO(String regionName, String metricName, double averageAmount) {
        this.regionName = regionName;
        this.metricName = metricName;
        this.averageAmount = averageAmount;
    }

    public String getRegionName() { return regionName; }
    public void setRegionName(String regionName) { this.regionName = regionName; }

    public String getMetricName() { return metricName; }
    public void setMetricName(String metricName) { this.metricName = metricName; }

    public double getAverageAmount() { return averageAmount; }
    public void setAverageAmount(double averageAmount) { this.averageAmount = averageAmount; }
}
