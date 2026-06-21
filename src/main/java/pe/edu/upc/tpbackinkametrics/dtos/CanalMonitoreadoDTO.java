package pe.edu.upc.tpbackinkametrics.dtos;

import pe.edu.upc.tpbackinkametrics.entities.Canal;
import pe.edu.upc.tpbackinkametrics.entities.Empresa;

public class CanalMonitoreadoDTO {
    private int idCanalMonitoreado;
    private Canal canal;
    private Empresa empresa;

    public int getIdCanalMonitoreado() { return idCanalMonitoreado; }
    public void setIdCanalMonitoreado(int idCanalMonitoreado) { this.idCanalMonitoreado = idCanalMonitoreado; }

    public Canal getCanal() { return canal; }
    public void setCanal(Canal canal) { this.canal = canal; }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
}
