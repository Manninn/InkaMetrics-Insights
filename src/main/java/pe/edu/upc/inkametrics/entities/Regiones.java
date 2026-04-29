package pe.edu.upc.inkametrics.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Regiones")
public class Regiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_region;
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    public Regiones(int id_region, String nombre) {
        this.id_region = id_region;
        this.nombre = nombre;
    }


    public Regiones() {

    }

    public int getId_region() {
        return id_region;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

