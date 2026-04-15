package pe.edu.upc.inkametrics.entities;
import jakarta.persistence.*;
@Entity
@Table(name = "Canales_monitoreados")
public class Canales_monitoreados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresas_clientes empresa;

    @ManyToOne
    @JoinColumn(name = "id_canal")
    private Canales canales;

    public Canales_monitoreados(int id, Empresas_clientes empresa, Canales canales) {
        this.id = id;
        this.empresa = empresa;
        this.canales = canales;
    }

    public Canales_monitoreados() {

    }

    public int getId() {
        return id;
    }

    public Empresas_clientes getEmpresa() {
        return empresa;
    }

    public Canales getCanales() {
        return canales;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmpresa(Empresas_clientes empresa) {
        this.empresa = empresa;
    }

    public void setCanales(Canales canales) {
        this.canales = canales;
    }
}
