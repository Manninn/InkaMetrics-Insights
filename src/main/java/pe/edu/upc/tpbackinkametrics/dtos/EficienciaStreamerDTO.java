package pe.edu.upc.tpbackinkametrics.dtos;

public class EficienciaStreamerDTO {
    private String nombreStreamer;

    public String getNombreStreamer() {
        return nombreStreamer;
    }

    public void setNombreStreamer(String nombreStreamer) {
        this.nombreStreamer = nombreStreamer;
    }

    public Double getRatioEficiencia() {
        return ratioEficiencia;
    }

    public void setRatioEficiencia(Double ratioEficiencia) {
        this.ratioEficiencia = ratioEficiencia;
    }

    private Double ratioEficiencia; // Segundos de marca / (Vistas / 1000)

    public EficienciaStreamerDTO(String nombreStreamer, Double ratioEficiencia) {
        this.nombreStreamer = nombreStreamer;
        this.ratioEficiencia = ratioEficiencia;
    }
}
