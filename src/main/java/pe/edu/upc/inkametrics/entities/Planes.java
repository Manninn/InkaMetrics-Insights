package pe.edu.upc.inkametrics.entities;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Planes")
public class Planes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_plan;
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    @Column(name = "precio_mensual", precision = 10, scale = 2)
    private int precio_mensual;
    @Column(name = "limite_api")
    private int limite_api;

    public Planes(int id_plan, String nombre, int precio_mensual, int limite_api) {
        this.id_plan = id_plan;
        this.nombre = nombre;
        this.precio_mensual = precio_mensual;
        this.limite_api = limite_api;
    }

    public Planes() {

    }

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
