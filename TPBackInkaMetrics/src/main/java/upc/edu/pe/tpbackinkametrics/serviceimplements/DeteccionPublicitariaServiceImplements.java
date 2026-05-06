package upc.edu.pe.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upc.edu.pe.tpbackinkametrics.entities.DeteccionPublicitaria;
import upc.edu.pe.tpbackinkametrics.repositories.IDeteccionPublicitariaRepository;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IDeteccionPublicitariaService;
import upc.edu.pe.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class DeteccionPublicitariaServiceImplements implements IDeteccionPublicitariaService {
    @Autowired
    private IDeteccionPublicitariaRepository dR;


    @Override
    public List<DeteccionPublicitaria> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean esCliente = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (esCliente) {
            int idEmpresa = SecurityUtils.getIdEmpresaActual();
            return dR.findByEmpresa(idEmpresa);
        }

        // El ADMIN tiene la vista global de todas las detecciones de todas las marcas
        return dR.findAll();    }

    @Override
    public DeteccionPublicitaria insert(DeteccionPublicitaria deteccionPublicitaria) {
        return dR.save(deteccionPublicitaria);
    }

    @Override
    public Optional<DeteccionPublicitaria> listId(int id) {
        return dR.findById(id);
    }

    @Override
    public DeteccionPublicitaria update(DeteccionPublicitaria deteccionPublicitaria) {
        return dR.save(deteccionPublicitaria);
    }

    @Override
    public void delete(int id) {
        dR.deleteById(id);
    }

    @Override
    public List<DeteccionPublicitaria> listByEmpresa(int idEmpresa) {
        return dR.findByEmpresa(idEmpresa);    }
}
