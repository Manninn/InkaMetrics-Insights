package pe.edu.upc.inkametrics.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Plataformas")
public class Plataformas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlataforma;
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    @Column(name = "url_base", length = 200)
    private String url_base;

    public Plataformas(int idPlataforma, String nombre, String url_base) {
        this.idPlataforma = idPlataforma;
        this.nombre = nombre;
        this.url_base = url_base;
    }

    public Plataformas() {

    }

    public int getId_plataforma() {
        return idPlataforma;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl_base() {
        return url_base;
    }

    public void setId_plataforma(int idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUrl_base(String url_base) {
        this.url_base = url_base;
    }
}
