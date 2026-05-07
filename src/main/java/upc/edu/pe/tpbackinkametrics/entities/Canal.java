package upc.edu.pe.tpbackinkametrics.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "canal")
public class Canal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdCanal;

    @Column(name = "UrlCanal", nullable = false, length = 100)
    private String UrlCanal;

    @Column(name = "SeguidoresActuales", nullable = false)
    private int SeguidoresActuales;

    @ManyToOne
    @JoinColumn(name = "IdPlataforma", nullable = false)
    private Plataforma Plataforma;

    @ManyToOne
    @JoinColumn(name = "IdStreamer", nullable = false)
    private Streamer Streamer;

    public Canal() {
    }

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
