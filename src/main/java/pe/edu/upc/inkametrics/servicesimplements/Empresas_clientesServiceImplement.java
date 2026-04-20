package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Empresas_clientes;
import pe.edu.upc.inkametrics.repositories.IEmpresas_clientesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IEmpresas_clientesService;

import java.util.List;
import java.util.Optional;

@Service
public class Empresas_clientesServiceImplement implements IEmpresas_clientesService {
    @Autowired
    private IEmpresas_clientesRepository cR;

    @Override
    public List<Empresas_clientes> list() {
        return cR.findAll();
    }

    @Override
    public Empresas_clientes insert(Empresas_clientes e) {
        return cR.save(e);
    }

    @Override
    public Optional<Empresas_clientes> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Empresas_clientes emp) {
        cR.save(emp);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
