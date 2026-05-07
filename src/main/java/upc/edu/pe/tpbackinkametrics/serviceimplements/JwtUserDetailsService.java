package upc.edu.pe.tpbackinkametrics.serviceimplements;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import upc.edu.pe.tpbackinkametrics.entities.Users;
import upc.edu.pe.tpbackinkametrics.repositories.IUserRepository;
import upc.edu.pe.tpbackinkametrics.securities.JwtUserDetails;


import java.util.ArrayList;
import java.util.List;


@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscamos al usuario en la base de datos
        Users user = repo.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("El usuario %s no existe en el sistema.", username));
        }

        // 2. Mapeamos los roles de la base de datos a SimpleGrantedAuthority
        List<GrantedAuthority> roles = new ArrayList<>();
        user.getRoles().forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(rol.getRol()));
        });

        // Log para que veas en la consola quién entra y con qué permisos
        System.out.println("Login exitoso - Usuario: " + username + " | Roles: " + roles);

        // 3. Extracción segura del ID de Empresa
        // Aunque tengas 'nullable = false', esta validación evita que el sistema "explote"
        // con un NullPointerException si la relación no carga correctamente.
        int idEmpresaFinal = 0;

        if (user.getEmpresa() != null) {
            idEmpresaFinal = user.getEmpresa().getIdEmpresa();
        } else {
            // Esto solo pasaría si hay un error de integridad, pero el 0
            // permite que el Admin siga entrando sin problemas.
            System.err.println("ADVERTENCIA: Usuario " + username + " no tiene objeto Empresa asociado.");
        }

        // 4. Retornamos tu clase personalizada JwtUserDetails
        // Asegúrate de que los parámetros coincidan con el orden de tu constructor
        return new JwtUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                idEmpresaFinal,
                roles
        );
    }
}