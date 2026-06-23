package pe.edu.upc.tpbackinkametrics.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdPlan;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String Nombre;

    @Column(name = "PrecioMensual", nullable = false)
    private Double PrecioMensual;

    @Column(name = "LimiteApi", nullable = false)
    private int LimiteApi;

    @JsonIgnore
    @OneToMany(mappedBy = "Plan", cascade = CascadeType.REMOVE)
    private List<Empresa> empresas;

    public Plan() {
    }

    public int getIdPlan() {
        return IdPlan;
    }

    public void setIdPlan(int idPlan) {
        IdPlan = idPlan;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Double getPrecioMensual() {
        return PrecioMensual;
    }

    public void setPrecioMensual(Double precioMensual) {
        PrecioMensual = precioMensual;
    }

    public Integer getLimiteApi() {
        return LimiteApi;
    }

    public void setLimiteApi(Integer limiteApi) {
        LimiteApi = limiteApi;
    }



}
