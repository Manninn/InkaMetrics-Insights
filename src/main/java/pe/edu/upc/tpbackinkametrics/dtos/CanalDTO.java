package pe.edu.upc.tpbackinkametrics.dtos;

import pe.edu.upc.tpbackinkametrics.entities.Plataforma;
import pe.edu.upc.tpbackinkametrics.entities.Streamer;

public class CanalDTO {
    private int idCanal;
    private String urlCanal;
    private int seguidoresActuales;
    private Plataforma plataforma;
    private Streamer streamer;

    public int getIdCanal() { return idCanal; }
    public void setIdCanal(int idCanal) { this.idCanal = idCanal; }

    public String getUrlCanal() { return urlCanal; }
    public void setUrlCanal(String urlCanal) { this.urlCanal = urlCanal; }

    public int getSeguidoresActuales() { return seguidoresActuales; }
    public void setSeguidoresActuales(int seguidoresActuales) { this.seguidoresActuales = seguidoresActuales; }

    public Plataforma getPlataforma() { return plataforma; }
    public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }

    public Streamer getStreamer() { return streamer; }
    public void setStreamer(Streamer streamer) { this.streamer = streamer; }
}
