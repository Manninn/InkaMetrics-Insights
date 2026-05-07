package upc.edu.pe.tpbackinkametrics.dtos;

import upc.edu.pe.tpbackinkametrics.entities.Plan;

public class EmpresaDTO {
    private int IdEmpresa;
    private String NombreComercial;
    private String Ruc;
    private Plan Plan;

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
