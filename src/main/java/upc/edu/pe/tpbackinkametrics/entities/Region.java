package upc.edu.pe.tpbackinkametrics.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdRegion;

    @Column(name = "Nombre", nullable = false, unique = true, length = 100)
    private String Nombre;

    public Region() {
    }

    public int getIdRegion() {
        return IdRegion;
    }

    public void setIdRegion(int idRegion) {
        IdRegion = idRegion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }


}
