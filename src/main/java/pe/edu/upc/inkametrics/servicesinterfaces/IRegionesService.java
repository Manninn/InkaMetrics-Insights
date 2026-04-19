package pe.edu.upc.inkametrics.servicesinterfaces;

import pe.edu.upc.inkametrics.entities.Regiones;
import java.util.List;

public interface IRegionesService {
    public void insert(Regiones regiones);
    public List<Regiones> list();
    public void delete(int id_region);
    public Regiones listId(int id_region);
}