package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Role;
import pe.edu.upc.tpbackinkametrics.entities.Users;
import pe.edu.upc.tpbackinkametrics.repositories.IUserRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplements implements IUserService {
    @Autowired
    private IUserRepository uR;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void insert(Users user) {
        // Forzar INSERT: el frontend manda id=0 por defecto, lo que confunde a Hibernate
        user.setId(null);

        // 1. Encriptar la contraseña (siempre primero)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 2. Lógica de Rol por Defecto
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            List<Role> rolesDefecto = new ArrayList<>();

            Role r = new Role();
            r.setRol("CLIENTE");
            r.setUser(user); // Establecemos la relación bidireccional

            rolesDefecto.add(r);
            user.setRoles(rolesDefecto);
        } else {
            // Si ya trae roles, nos aseguramos de que cada uno apunte al usuario
            user.getRoles().forEach(r -> r.setUser(user));
        }

        // 3. Guardar el usuario (gracias al CascadeType.ALL, el rol se guarda solo)
        uR.save(user);
    }

    @Override
    public List<Users> list() { return uR.findAll(); }

    @Override
    public void delete(Long idUser) { uR.deleteById(idUser); }

    @Override
    public Users listId(Long idUser) { return uR.findById(idUser).orElse(new Users()); }

    @Override
    public void update(Users user) {
        if (user.getRoles() != null) {
            user.getRoles().forEach(role -> role.setUser(user));
        }

        // Solo encriptamos si la contraseña cambió (no empieza con hash)
        if (!user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        uR.save(user);
    }
}
