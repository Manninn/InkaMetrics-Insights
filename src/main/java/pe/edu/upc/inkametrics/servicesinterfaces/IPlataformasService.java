package pe.edu.upc.inkametrics.servicesinterfaces;
import pe.edu.upc.inkametrics.entities.Plataformas;

import java.util.List;
import java.util.Optional;

public interface IPlataformasService {
    public List<Plataformas> list();

    public Plataformas insert(Plataformas p);

    Optional<Plataformas> listId(int id);

    public void update(Plataformas pla);

    public void delete(int id);
}
