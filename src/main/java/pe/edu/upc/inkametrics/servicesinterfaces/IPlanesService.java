package pe.edu.upc.inkametrics.servicesinterfaces;
import pe.edu.upc.inkametrics.entities.Planes;

import java.util.List;
import java.util.Optional;

public interface IPlanesService {
    public List<Planes> list();

    public Planes insert(Planes p);

    Optional<Planes> listId(int id);

    public void update(Planes pla);

    public void delete(int id);
}
