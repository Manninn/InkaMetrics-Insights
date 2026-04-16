package pe.edu.upc.inkametrics.entities;
import jakarta.persistence.*;
@Entity
@Table(name = "Metricas")
public class Metricas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombreMetrica")
    private String nameMetrica;
    @Column(name = "cantidadMetrica")
    private int cantidadMetrica;

    @ManyToOne
    @JoinColumn(name = "id_transmision")
    private Transmisiones transmisiones;

    public Metricas(int id, String nameMetrica, int cantidadMetrica, Transmisiones transmisiones) {
        this.id = id;
        this.nameMetrica = nameMetrica;
        this.cantidadMetrica = cantidadMetrica;
        this.transmisiones = transmisiones;
    }

    public Metricas() {

    }

    public int getId() {
        return id;
    }

    public String getNameMetrica() {
        return nameMetrica;
    }

    public int getCantidadMetrica() {
        return cantidadMetrica;
    }

    public Transmisiones getTransmisiones() {
        return transmisiones;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameMetrica(String nameMetrica) {
        this.nameMetrica = nameMetrica;
    }

    public void setCantidadMetrica(int cantidadMetrica) {
        this.cantidadMetrica = cantidadMetrica;
    }

    public void setTransmisiones(Transmisiones transmisiones) {
        this.transmisiones = transmisiones;
    }
}
