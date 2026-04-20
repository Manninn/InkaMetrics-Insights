package pe.edu.upc.inkametrics.entities;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Transmisiones")
public class Transmisiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_transmision;

    @Column(name = "titulo_stream", length = 200)
    private String titulo_stream;
    @Column(name = "fecha_inicio")
    private LocalDate fecha_inicio;
    @Column(name = "fecha_fin")
    private LocalDate fecha_fin;
    @Column(name = "en_vivo")
    private boolean en_vivo;

    @ManyToOne
    @JoinColumn(name = "id_canal")
    private Canales canales;

    public Transmisiones(int id_transmision, String titulo_stream, LocalDate fecha_inicio, LocalDate fecha_fin, boolean en_vivo, Canales canales) {
        this.id_transmision = id_transmision;
        this.titulo_stream = titulo_stream;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.en_vivo = en_vivo;
        this.canales = canales;
    }

    public Transmisiones() {

    }

    public int getId_transmision() {
        return id_transmision;
    }

    public String getTitulo_stream() {
        return titulo_stream;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public boolean isEn_vivo() {
        return en_vivo;
    }

    public Canales getCanales() {
        return canales;
    }

    public void setId_transmision(int id_transmision) {
        this.id_transmision = id_transmision;
    }

    public void setTitulo_stream(String titulo_stream) {
        this.titulo_stream = titulo_stream;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public void setEn_vivo(boolean en_vivo) {
        this.en_vivo = en_vivo;
    }

    public void setCanales(Canales canales) {
        this.canales = canales;
    }
}
