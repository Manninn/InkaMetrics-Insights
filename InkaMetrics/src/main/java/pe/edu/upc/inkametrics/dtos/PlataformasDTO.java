package pe.edu.upc.inkametrics.dtos;

public class PlataformasDTO {
    private int id_plataforma;
    private String nombre;
    private String url_base;

    public int getId_plataforma() {
        return id_plataforma;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl_base() {
        return url_base;
    }

    public void setId_plataforma(int id_plataforma) {
        this.id_plataforma = id_plataforma;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUrl_base(String url_base) {
        this.url_base = url_base;
    }
}
