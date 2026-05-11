package pe.edu.upc.tpbackinkametrics.entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "deteccionPublicitaria")
public class
DeteccionPublicitaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdDeteccionPublicitaria;

    @Column(name = "Tipo", nullable = false, length = 100)
    private String Tipo;

    @Column(name = "MinutoAparicion", nullable = false)
    private LocalTime MinutoAparicion;

    @Column(name = "TiempoAparicionSeg", nullable = false)
    private int TiempoAparicionSeg;

    @Column(name = "CantidadDetecciones", nullable = false)
    private Long CantidadDetecciones;

    @Column(name = "TotalDuracionSeg", nullable = false)
    private Long TotalDuracionSeg;

    @ManyToOne
    @JoinColumn(name = "IdTransmision", nullable = false)
    private Transmision Transmision;

    @ManyToOne
    @JoinColumn(name = "IdMarca", nullable = false)
    private Marca Marca;

    public DeteccionPublicitaria() {
    }

    public DeteccionPublicitaria(int idDeteccionPublicitaria, String tipo, LocalTime minutoAparicion, int tiempoAparicionSeg, Long cantidadDetecciones, Long totalDuracionSeg, Transmision transmision, Marca marca) {
        IdDeteccionPublicitaria = idDeteccionPublicitaria;
        Tipo = tipo;
        MinutoAparicion = minutoAparicion;
        TiempoAparicionSeg = tiempoAparicionSeg;
        CantidadDetecciones = cantidadDetecciones;
        TotalDuracionSeg = totalDuracionSeg;
        Transmision = transmision;
        Marca = marca;
    }

    public int getIdDeteccionPublicitaria() {
        return IdDeteccionPublicitaria;
    }

    public void setIdDeteccionPublicitaria(int idDeteccionPublicitaria) {
        IdDeteccionPublicitaria = idDeteccionPublicitaria;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public LocalTime getMinutoAparicion() {
        return MinutoAparicion;
    }

    public void setMinutoAparicion(LocalTime minutoAparicion) {
        MinutoAparicion = minutoAparicion;
    }

    public int getTiempoAparicionSeg() {
        return TiempoAparicionSeg;
    }

    public void setTiempoAparicionSeg(int tiempoAparicionSeg) {
        TiempoAparicionSeg = tiempoAparicionSeg;
    }

    public Long getCantidadDetecciones() {
        return CantidadDetecciones;
    }

    public void setCantidadDetecciones(Long cantidadDetecciones) {
        CantidadDetecciones = cantidadDetecciones;
    }

    public Long getTotalDuracionSeg() {
        return TotalDuracionSeg;
    }

    public void setTotalDuracionSeg(Long totalDuracionSeg) {
        TotalDuracionSeg = totalDuracionSeg;
    }

    public Transmision getTransmision() {
        return Transmision;
    }

    public void setTransmision(Transmision transmision) {
        Transmision = transmision;
    }

    public Marca getMarca() {
        return Marca;
    }

    public void setMarca(Marca marca) {
        Marca = marca;
    }
}
