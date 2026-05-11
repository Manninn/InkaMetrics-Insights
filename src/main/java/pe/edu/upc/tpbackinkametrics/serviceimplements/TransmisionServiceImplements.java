package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.dtos.TransmisionesStreamerDTO;
import pe.edu.upc.tpbackinkametrics.entities.Transmision;
import pe.edu.upc.tpbackinkametrics.repositories.ITransmisionRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.ITransmisionService;
import pe.edu.upc.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<TransmisionesStreamerDTO> reporteTransmisionesPorStreamer() {
        int idEmpresa = SecurityUtils.getIdEmpresaActual();
        List<String[]> data = tR.countTransmissionsByStreamer(idEmpresa);

        return data.stream()
                .map(f -> new TransmisionesStreamerDTO(f[0], Long.parseLong(f[1])))
                .collect(Collectors.toList());
    }
}
