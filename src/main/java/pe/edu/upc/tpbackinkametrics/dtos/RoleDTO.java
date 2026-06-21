package pe.edu.upc.tpbackinkametrics.dtos;

import pe.edu.upc.tpbackinkametrics.entities.Users;

public class RoleDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    private String rol;
    private UserDTO user;






}
