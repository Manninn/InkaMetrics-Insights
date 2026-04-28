package pe.edu.upc.inkametrics.dtos;

public class MetricasPorTransmisionDTO {
    private String tituloStream;
    private String nombreMetrica;
    private int totalCantidad;


    public MetricasPorTransmisionDTO(String tituloStream, String nombreMetrica, int totalCantidad) {
        this.tituloStream = tituloStream;
        this.nombreMetrica = nombreMetrica;
        this.totalCantidad = totalCantidad;
    }

    public String getTituloStream() {
        return tituloStream;
    }

    public String getNombreMetrica() {
        return nombreMetrica;
    }

    public int getTotalCantidad() {
        return totalCantidad;
    }

    public void setTituloStream(String tituloStream) {
        this.tituloStream = tituloStream;
    }

    public void setNombreMetrica(String nombreMetrica) {
        this.nombreMetrica = nombreMetrica;
    }

    public void setTotalCantidad(int totalCantidad) {
        this.totalCantidad = totalCantidad;
    }
}