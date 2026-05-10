package pe.edu.upc.tpbackinkametrics.dtos;

public class DeteccionPublicitariaCantidadDTO {
    private String nombreMarca;
    private Long cantidadDetecciones;

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public Long getCantidadDetecciones() {
        return cantidadDetecciones;
    }

    public void setCantidadDetecciones(Long cantidadDetecciones) {
        this.cantidadDetecciones = cantidadDetecciones;
    }
}
