package pe.edu.upc.inkametrics.dtos;


public class PlanesDTO {
    private int id_plan;
    private String nombre;
    private int precio_mensual;
    private int limite_api;

    public int getId_plan() {
        return id_plan;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio_mensual() {
        return precio_mensual;
    }

    public int getLimite_api() {
        return limite_api;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio_mensual(int precio_mensual) {
        this.precio_mensual = precio_mensual;
    }

    public void setLimite_api(int limite_api) {
        this.limite_api = limite_api;
    }
}
