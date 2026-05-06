package upc.edu.pe.tpbackinkametrics.dtos;


import upc.edu.pe.tpbackinkametrics.entities.Canal;
import upc.edu.pe.tpbackinkametrics.entities.Empresa;

public class CanalMonitoreadoDTO {

    private int IdCanalMonitoreado;
    private Canal Canal;
    private Empresa Empresa;

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
