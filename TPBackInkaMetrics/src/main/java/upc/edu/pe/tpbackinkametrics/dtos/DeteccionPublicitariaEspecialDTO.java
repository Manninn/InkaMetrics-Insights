package upc.edu.pe.tpbackinkametrics.dtos;


import upc.edu.pe.tpbackinkametrics.entities.Marca;
import upc.edu.pe.tpbackinkametrics.entities.Transmision;

import java.time.LocalTime;

public class DeteccionPublicitariaEspecialDTO {
    private int IdDeteccionPublicitaria;
    private String Tipo;
    private int TiempoAparicionSeg;
    private Transmision Transmision;
    private Marca Marca;

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

    public int getTiempoAparicionSeg() {
        return TiempoAparicionSeg;
    }

    public void setTiempoAparicionSeg(int tiempoAparicionSeg) {
        TiempoAparicionSeg = tiempoAparicionSeg;
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
