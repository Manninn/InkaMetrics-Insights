package pe.edu.upc.tpbackinkametrics.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "marca")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdMarca;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String Nombre;

    @Column(name = "Sector", nullable = false, length = 100)
    private String Sector;

    @JsonIgnore
    @OneToMany(mappedBy = "Marca", cascade = CascadeType.REMOVE)
    private List<DeteccionPublicitaria> detecciones;

    public Marca() {
    }
    public int getIdMarca() {
        return IdMarca;
    }

    public void setIdMarca(int idMarca) {
        IdMarca = idMarca;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
    }

}
