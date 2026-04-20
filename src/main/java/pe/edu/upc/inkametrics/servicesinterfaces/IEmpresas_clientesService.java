package pe.edu.upc.inkametrics.servicesinterfaces;
import pe.edu.upc.inkametrics.entities.Empresas_clientes;

import java.util.List;
import java.util.Optional;

public interface IEmpresas_clientesService {
    public List<Empresas_clientes> list();

    public Empresas_clientes insert(Empresas_clientes e);

    Optional<Empresas_clientes> listId(int id);

    public void update(Empresas_clientes emp);

    public void delete(int id);
}
