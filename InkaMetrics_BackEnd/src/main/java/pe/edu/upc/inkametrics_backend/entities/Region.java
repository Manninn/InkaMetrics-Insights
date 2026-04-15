package pe.edu.upc.inkametrics_backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRegion;

    @Column(name = "nameRegion", nullable = false)
    private String nameRegion;

    public Region() {
    }

    public Region(int idRegion, String nameRegion) {
        this.idRegion = idRegion;
        this.nameRegion = nameRegion;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }
}

