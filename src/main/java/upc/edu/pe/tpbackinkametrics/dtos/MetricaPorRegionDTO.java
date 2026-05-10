package upc.edu.pe.tpbackinkametrics.dtos;

public class MetricaPorRegionDTO {
    private String nombreRegion;
    private String nombreMetrica;
    private Double valorPromedio;

    // El constructor DEBE coincidir con el orden del SELECT en el Repository
    public MetricaPorRegionDTO(String nombreRegion, String nombreMetrica, Double valorPromedio) {
        this.nombreRegion = nombreRegion;
        this.nombreMetrica = nombreMetrica;
        this.valorPromedio = valorPromedio;
    }

    // Getters y Setters
    public String getNombreRegion() { return nombreRegion; }
    public void setNombreRegion(String nombreRegion) { this.nombreRegion = nombreRegion; }

    public String getNombreMetrica() { return nombreMetrica; }
    public void setNombreMetrica(String nombreMetrica) { this.nombreMetrica = nombreMetrica; }

    public Double getValorPromedio() { return valorPromedio; }
    public void setValorPromedio(Double valorPromedio) { this.valorPromedio = valorPromedio; }
}