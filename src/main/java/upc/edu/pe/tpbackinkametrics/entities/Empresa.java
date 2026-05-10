package upc.edu.pe.tpbackinkametrics.entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "empresa")

public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdEmpresa;

    @Column(name = "NombreComercial", nullable = false, length = 100)
    private String NombreComercial;

    @Column(name = "Ruc", nullable = false, length = 11)
    private String Ruc;

    @ManyToOne
    @JoinColumn(name = "IdPlan", nullable = false)
    private Plan Plan;


    public Empresa() {
    }

    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        IdEmpresa = idEmpresa;
    }

    public String getNombreComercial() {
        return NombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        NombreComercial = nombreComercial;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }

    public Plan getPlan() {
        return Plan;
    }

    public void setPlan(Plan plan) {
        Plan = plan;
    }

}
