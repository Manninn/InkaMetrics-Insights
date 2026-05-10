package upc.edu.pe.tpbackinkametrics.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "plataforma")
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdPlataforma;

    @Column(name = "Nombre", nullable = false, unique = true, length = 50)
    private String Nombre;

    @Column(name = "url_base", nullable = false, length = 200)
    private String urlBase;

    public Plataforma() {
    }

    public int getIdPlataforma() {
        return IdPlataforma;
    }

    public void setIdPlataforma(int idPlataforma) {
        this.IdPlataforma = idPlataforma;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }


}
