package pe.edu.upc.tpbackinkametrics.dtos;

import pe.edu.upc.tpbackinkametrics.entities.Canal;

import java.time.LocalDate;

public class TransmisionDTO {
    private int idTransmision;
    private String tituloStream;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean enVivo;
    private Canal canal;

    public int getIdTransmision() { return idTransmision; }
    public void setIdTransmision(int idTransmision) { this.idTransmision = idTransmision; }

    public String getTituloStream() { return tituloStream; }
    public void setTituloStream(String tituloStream) { this.tituloStream = tituloStream; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public boolean isEnVivo() { return enVivo; }
    public void setEnVivo(boolean enVivo) { this.enVivo = enVivo; }

    public Canal getCanal() { return canal; }
    public void setCanal(Canal canal) { this.canal = canal; }
}
