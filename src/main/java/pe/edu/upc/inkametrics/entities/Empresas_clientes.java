package pe.edu.upc.inkametrics.entities;
import jakarta.persistence.*;
@Entity
@Table(name = "Empresas_clientes")
public class Empresas_clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_empresa;
    @Column(name = "nombre_comercial", length = 100, nullable = false)
    private String nombre_comercial;
    @Column(name = "ruc", length = 11)
    private String ruc;

    @ManyToOne
    @JoinColumn(name = "id_plan")
    private Planes planes;

    public Empresas_clientes(int id_empresa, String nombre_comercial, String ruc, Planes planes) {
        this.id_empresa = id_empresa;
        this.nombre_comercial = nombre_comercial;
        this.ruc = ruc;
        this.planes = planes;
    }

    public Empresas_clientes() {

    }

    public int getId_empresa() {
        return id_empresa;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public String getRuc() {
        return ruc;
    }

    public Planes getPlanes() {
        return planes;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setPlanes(Planes planes) {
        this.planes = planes;
    }
}