package pe.edu.upc.inkametrics.servicesinterfaces;
import pe.edu.upc.inkametrics.entities.Roles;

import java.util.List;
import java.util.Optional;

public interface IRolesService {
    public List<Roles> list();

    public Roles insert(Roles r);

    Optional<Roles> listId(int id);

    public void update(Roles rol);

    public void delete(int id);
}
