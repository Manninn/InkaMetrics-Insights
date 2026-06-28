package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Role;
import pe.edu.upc.tpbackinkametrics.repositories.IRoleRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IRoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public void insert(Role role) { role.setId(null); roleRepository.save(role); }

    @Override
    public List<Role> list() { return roleRepository.findAll(); }

    @Override
    public void delete(Long idRole) { roleRepository.deleteById(idRole); }

    @Override
    public Role listId(Long idRole) { return roleRepository.findById(idRole).orElse(new Role()); }

    @Override
    public void update(Role role) { roleRepository.save(role); }
}
