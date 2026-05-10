package upc.edu.pe.tpbackinkametrics.dtos;

import upc.edu.pe.tpbackinkametrics.entities.Region;

import java.time.LocalDate;

public class StreamerEspecialDTO {
    private int IdStreamer;
    private String Nickname;
    private String Genero;
    private Region region;


    public int getIdStreamer() {
        return IdStreamer;
    }

    public void setIdStreamer(int idStreamer) {
        IdStreamer = idStreamer;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
