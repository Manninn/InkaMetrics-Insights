package pe.edu.upc.inkametrics.servicesinterfaces;
import pe.edu.upc.inkametrics.entities.Transmisiones;

import java.util.List;
import java.util.Optional;

public interface ITransmisionesService {
    public List<Transmisiones> list();

    public Transmisiones insert(Transmisiones tra);

    Optional<Transmisiones> listId(int id);

    public void update(Transmisiones tra);

    public void delete(int id);
}
