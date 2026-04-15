package pe.edu.upc.inkametrics.dtos;

public class PlataformasDTO {
    private int idplataforma;
    private String nombre;
    private String url_base;

    public int getId_plataforma() {
        return idplataforma;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl_base() {
        return url_base;
    }

    public void setId_plataforma(int id_plataforma) {
        this.idplataforma = id_plataforma;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUrl_base(String url_base) {
        this.url_base = url_base;
    }
}
