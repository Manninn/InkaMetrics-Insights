package pe.edu.upc.inkametrics.dtos;

public class PlataformaCreateDTO {
    private String nombre;
    private String url_base;

    public String getNombre() {
        return nombre;
    }

    public String getUrl_base() {
        return url_base;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUrl_base(String url_base) {
        this.url_base = url_base;
    }
}
