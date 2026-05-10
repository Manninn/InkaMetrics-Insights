package upc.edu.pe.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upc.edu.pe.tpbackinkametrics.entities.Empresa;
import upc.edu.pe.tpbackinkametrics.repositories.IEmpresaRepository;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IEmpresaService;
import upc.edu.pe.tpbackinkametrics.util.SecurityUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImplements implements IEmpresaService {

    @Autowired
    private IEmpresaRepository eR;

    @Override
    public List<Empresa> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean esCliente = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (esCliente) {
            // Si es cliente, solo le devolvemos una lista con SU empresa
            int idEmpresa = SecurityUtils.getIdEmpresaActual();
            return eR.findById(idEmpresa).map(List::of).orElse(Collections.emptyList());
        }

        // Si es ADMIN, ve todas las empresas registradas
        return eR.findAll();    }

    @Override
    public Empresa insert(Empresa empresa) {
        return eR.save(empresa);
    }

    @Override
    public Optional<Empresa> listId(int id) {
        return eR.findById(id);
    }

    @Override
    public Empresa update(Empresa empresa) {
        return eR.save(empresa);
    }

    @Override
    public void delete(int id) {
        eR.deleteById(id);
    }
}
