package pe.edu.upc.tpbackinkametrics.dtos;

import jakarta.persistence.Column;

public class PlanDTO {
    private int idPlan;
    private String nombre;
    private Double precioMensual;
    private int limiteApi;

    public int getIdPlan() { return idPlan; }
    public void setIdPlan(int idPlan) { this.idPlan = idPlan; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecioMensual() { return precioMensual; }
    public void setPrecioMensual(Double precioMensual) { this.precioMensual = precioMensual; }

    public int getLimiteApi() { return limiteApi; }
    public void setLimiteApi(int limiteApi) { this.limiteApi = limiteApi; }
}
