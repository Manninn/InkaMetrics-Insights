package pe.edu.upc.inkametrics.servicesinterfaces;

import pe.edu.upc.inkametrics.entities.Canales;

import java.util.List;
import java.util.Optional;

public interface ICanalesService {
    public List<Canales> list();

    public Canales insert(Canales c);

    Optional<Canales> listId(int id);

    public void update(Canales can);

    public void delete(int id);
}