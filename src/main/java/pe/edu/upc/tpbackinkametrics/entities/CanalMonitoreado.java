package pe.edu.upc.tpbackinkametrics.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "canalMonitoreado")
public class CanalMonitoreado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdCanalMonitoreado;

    @ManyToOne
    @JoinColumn(name = "IdCanal", nullable = false)
    private Canal Canal;

    @ManyToOne
    @JoinColumn(name = "IdEmpresa", nullable = false)
    private Empresa Empresa;

    public CanalMonitoreado() {
    }

    public CanalMonitoreado(int idCanalMonitoreado, Canal canal, Empresa empresa) {
        IdCanalMonitoreado = idCanalMonitoreado;
        Canal = canal;
        Empresa = empresa;
    }

    public int getIdCanalMonitoreado() {
        return IdCanalMonitoreado;
    }

    public void setIdCanalMonitoreado(int idCanalMonitoreado) {
        IdCanalMonitoreado = idCanalMonitoreado;
    }

    public Canal getCanal() {
        return Canal;
    }

    public void setCanal(Canal canal) {
        Canal = canal;
    }

    public Empresa getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(Empresa empresa) {
        Empresa = empresa;
    }

}
