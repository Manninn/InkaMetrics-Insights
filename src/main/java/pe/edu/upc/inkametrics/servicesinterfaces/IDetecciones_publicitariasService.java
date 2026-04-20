package pe.edu.upc.inkametrics.servicesinterfaces;
import pe.edu.upc.inkametrics.entities.Detecciones_publicitarias;

import java.util.List;
import java.util.Optional;

public interface IDetecciones_publicitariasService {
    public List<Detecciones_publicitarias> list();

    public Detecciones_publicitarias insert(Detecciones_publicitarias d);

    Optional<Detecciones_publicitarias> listId(int id);

    public void update(Detecciones_publicitarias det);

    public void delete(int id);
}
