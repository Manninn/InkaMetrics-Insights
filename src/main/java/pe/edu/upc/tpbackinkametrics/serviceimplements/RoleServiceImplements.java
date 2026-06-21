package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Role;
import pe.edu.upc.tpbackinkametrics.repositories.IRoleRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IRoleService;

import java.util.List;

@Service
public class RoleServiceImplements implements IRoleService {
    @Autowired
    private IRoleRepository rR;

    @Override
    public void insert(Role role) { rR.save(role); }

    @Override
    public List<Role> list() { return rR.findAll(); }

    @Override
    public void delete(Long idRole) { rR.deleteById(idRole); }

    @Override
    public Role listId(Long idRole) { return rR.findById(idRole).orElse(new Role()); }

    @Override
    public void update(Role role) { rR.save(role); }
}
