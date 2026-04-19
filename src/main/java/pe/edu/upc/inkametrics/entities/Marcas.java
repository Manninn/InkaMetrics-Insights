package pe.edu.upc.inkametrics.entities;
import jakarta.persistence.*;
@Entity
@Table(name = "Marcas")
public class Marcas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_marca;
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    @Column(name = "sector", length = 100)
    private String sector;

    public Marcas(int id_marca, String nombre, String sector) {
        this.id_marca = id_marca;
        this.nombre = nombre;
        this.sector = sector;
    }

    public Marcas() {

    }

    public int getId_marca() {
        return id_marca;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSector() {
        return sector;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}