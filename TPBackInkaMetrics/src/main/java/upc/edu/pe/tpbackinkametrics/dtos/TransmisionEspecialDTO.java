package upc.edu.pe.tpbackinkametrics.dtos;

import upc.edu.pe.tpbackinkametrics.entities.Canal;

import java.time.LocalDate;

public class TransmisionEspecialDTO {
    private int IdTransmision;
    private String TituloStream;
    private LocalDate FechaFin;
    private boolean EnVivo;
    private Canal canal;

    public int getIdTransmision() {
        return IdTransmision;
    }

    public void setIdTransmision(int idTransmision) {
        IdTransmision = idTransmision;
    }

    public String getTituloStream() {
        return TituloStream;
    }

    public void setTituloStream(String tituloStream) {
        TituloStream = tituloStream;
    }

    public LocalDate getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        FechaFin = fechaFin;
    }

    public boolean isEnVivo() {
        return EnVivo;
    }

    public void setEnVivo(boolean enVivo) {
        EnVivo = enVivo;
    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }
}
