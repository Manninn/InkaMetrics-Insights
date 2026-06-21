package pe.edu.upc.tpbackinkametrics.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import pe.edu.upc.tpbackinkametrics.entities.Empresa;

public class UserDTO {
    private Long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // 👈 LA LÍNEA MÁGICA
    private String password;
    private Boolean enabled;
    private Empresa empresa;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
