package pe.edu.upc.inkametrics.servicesinterfaces;
import pe.edu.upc.inkametrics.entities.Plataformas;

import java.util.List;
import java.util.Optional;

public interface IPlataformasService {
    //Listar.
    public List<Plataformas> list();

    //Registrar.
    public Plataformas insert(Plataformas plataformas);

    //Actualizar.
    public Plataformas update(Plataformas plataformas);
    public Optional<Plataformas>ListId(int id);

    //Eliminar.
    public void delete(int id);
}
