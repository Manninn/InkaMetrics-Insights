package pe.edu.upc.inkametrics.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Canales")
public class Canales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_canal;
    @Column(name = "url_canal",length = 200)
    private String url_canal;
    @Column(name = "seguidores_actuales")
    private int seguidores_actuales;

    @ManyToOne
    @JoinColumn(name = "id_streamer")
    private Streamers streamer;

    @ManyToOne
    @JoinColumn(name = "id_plataforma")
    private Plataformas plataforma;

    public Canales(int id_canal, String url_canal, int seguidores_actuales, Streamers streamer, Plataformas plataforma) {
        this.id_canal = id_canal;
        this.url_canal = url_canal;
        this.seguidores_actuales = seguidores_actuales;
        this.streamer = streamer;
        this.plataforma = plataforma;
    }

    public Canales() {

    }

    public int getId_canal() {
        return id_canal;
    }

    public String getUrl_canal() {
        return url_canal;
    }

    public int getSeguidores_actuales() {
        return seguidores_actuales;
    }

    public Streamers getStreamer() {
        return streamer;
    }

    public Plataformas getPlataforma() {
        return plataforma;
    }

    public void setId_canal(int id_canal) {
        this.id_canal = id_canal;
    }

    public void setUrl_canal(String url_canal) {
        this.url_canal = url_canal;
    }

    public void setSeguidores_actuales(int seguidores_actuales) {
        this.seguidores_actuales = seguidores_actuales;
    }

    public void setStreamer(Streamers streamer) {
        this.streamer = streamer;
    }

    public void setPlataforma(Plataformas plataforma) {
        this.plataforma = plataforma;
    }
}
