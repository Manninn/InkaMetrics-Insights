package pe.edu.upc.inkametrics.servicesinterfaces;

import pe.edu.upc.inkametrics.entities.Canales_monitoreados;

import java.util.List;
import java.util.Optional;

// se usan historias de usuario aquí
public interface ICanales_monitoreadosService {
    public List<Canales_monitoreados> list();

    public Canales_monitoreados insert(Canales_monitoreados c);

    public Optional<Canales_monitoreados> listId(int id);

    public void update(Canales_monitoreados can);

    public void delete(int id);
}
