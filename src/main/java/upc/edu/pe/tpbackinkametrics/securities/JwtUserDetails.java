package upc.edu.pe.tpbackinkametrics.securities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final int idEmpresa; // <--- El dato que necesitamos para filtrar
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(Long id, String username, String password, int idEmpresa, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.idEmpresa = idEmpresa;
        this.authorities = authorities;
    }

    public int getIdEmpresa() { return idEmpresa; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;    }

    @Override
    public String getPassword() {
        return password;    }

    @Override
    public String getUsername() {
        return username;    }

    @Override
    public boolean isAccountNonExpired() {
        return true;    }

    @Override
    public boolean isAccountNonLocked() {
        return true;    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;    }

    @Override
    public boolean isEnabled() {
        return true;    }
}
