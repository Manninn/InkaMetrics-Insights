package pe.edu.upc.inkametrics_backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "platform")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlatform;

    @Column(name = "namePlatform", nullable = false)
    private String namePlatform;

    @Column(name = "urlBase", nullable = false)
    private String urlBase;

    public Platform() {
    }

    public Platform(int idPlatform, String namePlatform, String urlBase) {
        this.idPlatform = idPlatform;
        this.namePlatform = namePlatform;
        this.urlBase = urlBase;
    }

    public int getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(int idPlatform) {
        this.idPlatform = idPlatform;
    }

    public String getNamePlatform() {
        return namePlatform;
    }

    public void setNamePlatform(String namePlatform) {
        this.namePlatform = namePlatform;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }
}
