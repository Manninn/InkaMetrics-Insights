package pe.edu.upc.inkametrics.servicesinterfaces;
import pe.edu.upc.inkametrics.entities.Roles;
import pe.edu.upc.inkametrics.entities.Streamers;

import java.util.List;
import java.util.Optional;

public interface IStreamersService {
    public List<Streamers> list();

    public Streamers insert(Streamers s);

    Optional<Streamers> listId(int id);

    public void update(Streamers str);

    public void delete(int id);
}
