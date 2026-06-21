package pe.edu.upc.tpbackinkametrics.dtos;

import pe.edu.upc.tpbackinkametrics.entities.Plan;

public class EmpresaDTO {
    private int idEmpresa;
    private String nombreComercial;
    private String ruc;
    private Plan plan;

    public int getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(int idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }

    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }

    public Plan getPlan() { return plan; }
    public void setPlan(Plan plan) { this.plan = plan; }
}
