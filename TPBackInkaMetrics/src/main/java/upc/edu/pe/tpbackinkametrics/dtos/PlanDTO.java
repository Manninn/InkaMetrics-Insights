package upc.edu.pe.tpbackinkametrics.dtos;

import jakarta.persistence.Column;

public class PlanDTO {
    private int IdPlan;
    private String Nombre;
    private Double PrecioMensual;
    private int LimiteApi;

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

    public int getLimiteApi() {
        return LimiteApi;
    }

    public void setLimiteApi(int limiteApi) {
        LimiteApi = limiteApi;
    }
}
