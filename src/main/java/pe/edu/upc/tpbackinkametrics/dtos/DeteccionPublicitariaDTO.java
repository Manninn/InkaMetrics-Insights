package pe.edu.upc.tpbackinkametrics.dtos;

import pe.edu.upc.tpbackinkametrics.entities.Marca;
import pe.edu.upc.tpbackinkametrics.entities.Transmision;

import java.time.LocalTime;

public class DeteccionPublicitariaDTO {
    private int idDeteccionPublicitaria;
    private String tipo;
    private LocalTime minutoAparicion;
    private int tiempoAparicionSeg;
    private Transmision transmision;
    private Marca marca;

    public int getIdDeteccionPublicitaria() { return idDeteccionPublicitaria; }
    public void setIdDeteccionPublicitaria(int idDeteccionPublicitaria) { this.idDeteccionPublicitaria = idDeteccionPublicitaria; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalTime getMinutoAparicion() { return minutoAparicion; }
    public void setMinutoAparicion(LocalTime minutoAparicion) { this.minutoAparicion = minutoAparicion; }

    public int getTiempoAparicionSeg() { return tiempoAparicionSeg; }
    public void setTiempoAparicionSeg(int tiempoAparicionSeg) { this.tiempoAparicionSeg = tiempoAparicionSeg; }

    public Transmision getTransmision() { return transmision; }
    public void setTransmision(Transmision transmision) { this.transmision = transmision; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }
}
