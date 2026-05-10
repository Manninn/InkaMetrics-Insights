package upc.edu.pe.tpbackinkametrics.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transmision")
public class Transmision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdTransmision;

    @Column(name = "TituloStream", nullable = false, length = 100)
    private String TituloStream;

    @Column(name = "FechaInicio", nullable = false)
    private LocalDate FechaInicio;

    @Column(name = "FechaFin", nullable = false)
    private LocalDate FechaFin;

    @Column(name = "EnVivo", nullable = false)
    private boolean EnVivo;

    @ManyToOne
    @JoinColumn(name = "IdCanal", nullable = false)
    private Canal canal;

    public Transmision() {
    }

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

    public LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        FechaInicio = fechaInicio;
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
