package pe.edu.upc.inkametrics.servicesinterfaces;
import pe.edu.upc.inkametrics.entities.Regiones;

import java.util.List;
import java.util.Optional;

public interface IRegionesService{
    public List<Regiones> list();

    public Regiones insert(Regiones r);

    Optional<Regiones> listId(int id);

    public void update(Regiones reg);

    public void delete(int id);
}
