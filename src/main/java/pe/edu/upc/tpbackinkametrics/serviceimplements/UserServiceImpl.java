package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Role;
import pe.edu.upc.tpbackinkametrics.entities.User;
import pe.edu.upc.tpbackinkametrics.repositories.IUserRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void insert(User user) {
        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            List<Role> defaultRoles = new ArrayList<>();
            Role r = new Role();
            r.setRole("CLIENTE");
            r.setUser(user);
            defaultRoles.add(r);
            user.setRoles(defaultRoles);
        } else {
            user.getRoles().forEach(r -> r.setUser(user));
        }

        userRepository.save(user);
    }

    @Override
    public List<User> list() { return userRepository.findAll(); }

    @Override
    public void delete(Long idUser) { userRepository.deleteById(idUser); }

    @Override
    public User listId(Long idUser) { return userRepository.findById(idUser).orElse(new User()); }

    @Override
    public void update(User user) {
        if (user.getRoles() != null) {
            user.getRoles().forEach(role -> role.setUser(user));
        }

        if (!user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userRepository.save(user);
    }
}
