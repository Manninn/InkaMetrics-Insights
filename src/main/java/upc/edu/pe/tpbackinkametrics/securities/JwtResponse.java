package upc.edu.pe.tpbackinkametrics.securities;

import java.io.Serializable;

/*
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

clase 5
@AllArgsConstructor
@Getter*/
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private String jwttoken;
    private String username;
    private String role;

    public JwtResponse() {
    }

    public JwtResponse(String jwttoken, String username, String role) {
        this.jwttoken = jwttoken;
        this.username = username;
        this.role = role;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public void setJwttoken(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
