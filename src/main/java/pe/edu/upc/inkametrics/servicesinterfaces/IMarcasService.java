package pe.edu.upc.inkametrics.servicesinterfaces;

import pe.edu.upc.inkametrics.entities.Empresas_clientes;
import pe.edu.upc.inkametrics.entities.Marcas;
import java.util.List;
import java.util.Optional;

public interface IMarcasService {
    public List<Marcas> list();

    public Marcas insert(Marcas m);

    Optional<Marcas> listId(int id);

    public void update(Marcas mar);

    public void delete(int id);
}
