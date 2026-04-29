package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Empresas_clientes;
import pe.edu.upc.inkametrics.repositories.IEmpresas_clientesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IEmpresas_clientesService;

import java.util.List;

@Service
public class Empresas_clientesServiceImplement implements IEmpresas_clientesService {
    @Autowired
    private IEmpresas_clientesRepository cR;

    @Override
    public List<Empresas_clientes> list() {
        return List.of();
    }
}
