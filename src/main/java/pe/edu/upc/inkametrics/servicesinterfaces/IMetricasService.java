package pe.edu.upc.inkametrics.servicesinterfaces;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.inkametrics.entities.Metricas;

public interface IMetricasService {
    public List<Metricas> list();
    public Metricas insert(Metricas m);
    public Optional<Metricas> listId(int id);
    public void update(Metricas m);
    public void delete(int id);
}
