package upc.edu.pe.tpbackinkametrics.dtos;


import upc.edu.pe.tpbackinkametrics.entities.Plataforma;
import upc.edu.pe.tpbackinkametrics.entities.Streamer;

public class CanalDTO {
    private int IdCanal;
    private String UrlCanal;
    private int SeguidoresActuales;
    private Plataforma Plataforma;
    private Streamer Streamer;

    public int getIdCanal() {
        return IdCanal;
    }

    public void setIdCanal(int idCanal) {
        IdCanal = idCanal;
    }

    public String getUrlCanal() {
        return UrlCanal;
    }

    public void setUrlCanal(String urlCanal) {
        UrlCanal = urlCanal;
    }

    public int getSeguidoresActuales() {
        return SeguidoresActuales;
    }

    public void setSeguidoresActuales(int seguidoresActuales) {
        SeguidoresActuales = seguidoresActuales;
    }

    public Plataforma getPlataforma() {
        return Plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        Plataforma = plataforma;
    }

    public Streamer getStreamer() {
        return Streamer;
    }

    public void setStreamer(Streamer streamer) {
        Streamer = streamer;
    }

}
