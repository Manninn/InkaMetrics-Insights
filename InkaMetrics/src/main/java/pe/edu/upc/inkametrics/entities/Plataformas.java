package pe.edu.upc.inkametrics.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Plataformas")
public class Plataformas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_plataforma;
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    @Column(name = "url_base", length = 200)
    private String url_base;

    public Plataformas(int id_plataforma, String nombre, String url_base) {
        this.id_plataforma = id_plataforma;
        this.nombre = nombre;
        this.url_base = url_base;
    }

    public Plataformas() {

    }

    public int getId_plataforma() {
        return id_plataforma;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl_base() {
        return url_base;
    }

    public void setId_plataforma(int id_plataforma) {
        this.id_plataforma = id_plataforma;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUrl_base(String url_base) {
        this.url_base = url_base;
    }
}
