package pe.edu.upc.tpbackinkametrics.dtos;

import pe.edu.upc.tpbackinkametrics.entities.Region;

import java.time.LocalDate;

public class StreamerDTO {
    private int idStreamer;
    private String nickname;
    private String genero;
    private LocalDate fechaRegistroApp;
    private Region region;

    public int getIdStreamer() { return idStreamer; }
    public void setIdStreamer(int idStreamer) { this.idStreamer = idStreamer; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public LocalDate getFechaRegistroApp() { return fechaRegistroApp; }
    public void setFechaRegistroApp(LocalDate fechaRegistroApp) { this.fechaRegistroApp = fechaRegistroApp; }

    public Region getRegion() { return region; }
    public void setRegion(Region region) { this.region = region; }
}
