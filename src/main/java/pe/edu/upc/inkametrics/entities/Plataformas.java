package pe.edu.upc.inkametrics.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Plataformas")
public class Plataformas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idplataforma;
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    @Column(name = "url_base", length = 200)
    private String url_base;

    @OneToMany(mappedBy = "plataforma")
    @JsonIgnore
    private List<Canales>canales;

    public Plataformas(int idplataforma, String nombre, String url_base) {
        this.idplataforma = idplataforma;
        this.nombre = nombre;
        this.url_base = url_base;
    }

    public Plataformas() {

    }

    public int getId_plataforma() {
        return idplataforma;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl_base() {
        return url_base;
    }

    public void setId_plataforma(int id_plataforma) {
        this.idplataforma = id_plataforma;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUrl_base(String url_base) {
        this.url_base = url_base;
    }
}
