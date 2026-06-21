package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.Role;

import java.util.List;

public interface IRoleService {
    public void insert(Role role);
    public List<Role> list();
    public void delete(Long idRole);
    public Role listId(Long idRole);
    public void update(Role role);
}
