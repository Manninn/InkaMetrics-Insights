package pe.edu.upc.inkametrics.entities;
import jakarta.persistence.*;
@Entity
@Table(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_role;
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    public Roles(int id_role, String nombre) {
        this.id_role = id_role;
        this.nombre = nombre;
    }

    public Roles() {

    }

    public int getId_role() {
        return id_role;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
