package pe.edu.upc.inkametrics.entities;
import jakarta.persistence.*;
@Entity
@Table(name = "Detecciones_publicitarias")
public class Detecciones_publicitarias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tipo", length = 100)
    private String tipo;
    @Column(name = "segundo_aparicion")
    private int segundo_aparicion;
    @Column(name = "duracion_seg")
    private int duracion_seg;

    @ManyToOne
    @JoinColumn(name = "id_transmision")
    private Transmisiones transmisiones;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marcas marcas;

    public Detecciones_publicitarias(int id, String tipo, int segundo_aparicion, int duracion_seg, Transmisiones transmisiones, Marcas marcas) {
        this.id = id;
        this.tipo = tipo;
        this.segundo_aparicion = segundo_aparicion;
        this.duracion_seg = duracion_seg;
        this.transmisiones = transmisiones;
        this.marcas = marcas;
    }

    public Detecciones_publicitarias() {

    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getSegundo_aparicion() {
        return segundo_aparicion;
    }

    public int getDuracion_seg() {
        return duracion_seg;
    }

    public Transmisiones getTransmisiones() {
        return transmisiones;
    }

    public Marcas getMarcas() {
        return marcas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setSegundo_aparicion(int segundo_aparicion) {
        this.segundo_aparicion = segundo_aparicion;
    }

    public void setDuracion_seg(int duracion_seg) {
        this.duracion_seg = duracion_seg;
    }

    public void setTransmisiones(Transmisiones transmisiones) {
        this.transmisiones = transmisiones;
    }

    public void setMarcas(Marcas marcas) {
        this.marcas = marcas;
    }
}
