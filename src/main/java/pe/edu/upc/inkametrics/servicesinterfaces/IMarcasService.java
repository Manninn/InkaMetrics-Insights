package pe.edu.upc.inkametrics.servicesinterfaces;

import pe.edu.upc.inkametrics.entities.Marcas;
import java.util.List;

public interface IMarcasService {
    public void insert(Marcas marcas);
    public List<Marcas> list();
    public void delete(int id_marca);
    public Marcas listId(int id_marca);

    // Nueva función agregada
    public List<Marcas> buscarPorNombre(String nombre);
}