package upc.edu.pe.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upc.edu.pe.tpbackinkametrics.entities.Transmision;
import upc.edu.pe.tpbackinkametrics.repositories.ITransmisionRepository;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.ITransmisionService;
import upc.edu.pe.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class TransmisionServiceImplements implements ITransmisionService {
    @Autowired
    private ITransmisionRepository tR;


    @Override
    public List<Transmision> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean esCliente = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (esCliente) {
            int idEmpresa = SecurityUtils.getIdEmpresaActual();
            return tR.findByEmpresa(idEmpresa);
        }

        // El ADMIN puede ver el historial de todas las transmisiones del sistema
        return tR.findAll();    }

    @Override
    public Transmision insert(Transmision transmision) {
        return tR.save(transmision);
    }

    @Override
    public Optional<Transmision> listId(int id) {
        return tR.findById(id);
    }

    @Override
    public Transmision update(Transmision transmision) {
        return tR.save(transmision);
    }

    @Override
    public void delete(int id) {
        tR.deleteById(id);
    }

    @Override
    public List<Transmision> listByEmpresa(int idEmpresa) {
        return tR.findByEmpresa(idEmpresa);
    }
}
