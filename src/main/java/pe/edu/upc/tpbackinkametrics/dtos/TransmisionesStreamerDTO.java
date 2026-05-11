package pe.edu.upc.tpbackinkametrics.dtos;

public class TransmisionesStreamerDTO {
    private String nombreStreamer;
    private Long totalTransmisiones;

    public String getNombreStreamer() {
        return nombreStreamer;
    }

    public void setNombreStreamer(String nombreStreamer) {
        this.nombreStreamer = nombreStreamer;
    }

    public Long getTotalTransmisiones() {
        return totalTransmisiones;
    }

    public void setTotalTransmisiones(Long totalTransmisiones) {
        this.totalTransmisiones = totalTransmisiones;
    }


    public TransmisionesStreamerDTO(String nombreStreamer, Long totalTransmisiones) {
        this.nombreStreamer = nombreStreamer;
        this.totalTransmisiones = totalTransmisiones;
    }






}
