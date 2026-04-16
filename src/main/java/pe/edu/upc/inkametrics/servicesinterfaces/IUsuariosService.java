package pe.edu.upc.inkametrics.servicesinterfaces;
import pe.edu.upc.inkametrics.entities.Usuarios;

import java.util.List;
import java.util.Optional;

public interface IUsuariosService {
    public List<Usuarios> list();

    public Usuarios insert(Usuarios u);

    public Optional<Usuarios> listId(int id);

    public void update(Usuarios u);

    public void delete(int id);
}
