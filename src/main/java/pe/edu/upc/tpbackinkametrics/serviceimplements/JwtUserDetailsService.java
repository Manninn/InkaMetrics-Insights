package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.User;
import pe.edu.upc.tpbackinkametrics.repositories.IUserRepository;
import pe.edu.upc.tpbackinkametrics.securities.JwtUserDetails;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("El usuario %s no existe en el sistema.", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        user.getRoles().forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(rol.getRole()));
        });

        System.out.println("Login exitoso - Usuario: " + username + " | Roles: " + roles);

        int companyIdFinal = 0;
        if (user.getCompany() != null) {
            companyIdFinal = user.getCompany().getId();
        } else {
            System.err.println("ADVERTENCIA: Usuario " + username + " no tiene objeto Company asociado.");
        }

        return new JwtUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                companyIdFinal,
                roles
        );
    }
}
