package pe.edu.upc.inkametrics.dtos;


public class UsuariosDTO {
    private int id;
    private String email;
    private String password;

    public int getIdUsuario() {
        return id;
    }

    public String getEmailUsuario() {
        return email;
    }

    public String getPasswordUsuario() {
        return password;
    }

    public void setIdUsuario(int id) {
        this.id = id;
    }

    public void setEmailUsuario(String email) {
        this.email = email;
    }

    public void setPasswordUsuario(String password) {
        this.password = password;
    }
}
