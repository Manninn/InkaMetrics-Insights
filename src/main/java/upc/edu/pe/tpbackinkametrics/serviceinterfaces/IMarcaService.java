package upc.edu.pe.tpbackinkametrics.serviceinterfaces;


import upc.edu.pe.tpbackinkametrics.entities.Marca;
import upc.edu.pe.tpbackinkametrics.entities.Streamer;

import java.util.List;
import java.util.Optional;

public interface IMarcaService {
    public List<Marca> list();
    public Marca insert(Marca marca);
    public Optional<Marca> listId(int id);
    public Marca update(Marca marca);
    public void delete(int id);
}
