package pe.edu.upc.tpbackinkametrics.serviceinterfaces;


import pe.edu.upc.tpbackinkametrics.entities.Marca;
import pe.edu.upc.tpbackinkametrics.entities.Streamer;

import java.util.List;
import java.util.Optional;

public interface IMarcaService {
    public List<Marca> list();
    public Marca insert(Marca marca);
    public Optional<Marca> listId(int id);
    public Marca update(Marca marca);
    public void delete(int id);
}
