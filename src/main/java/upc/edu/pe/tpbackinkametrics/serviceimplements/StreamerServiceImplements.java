package upc.edu.pe.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upc.edu.pe.tpbackinkametrics.entities.Streamer;
import upc.edu.pe.tpbackinkametrics.repositories.IStreamerRepository;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IStreamerService;
import upc.edu.pe.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class StreamerServiceImplements implements IStreamerService {
    @Autowired
    private IStreamerRepository sR;


    @Override
    public List<Streamer> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean esCliente = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (esCliente) {
            // Si es cliente, el sistema lo obliga a ver solo su empresa
            int idEmpresa = SecurityUtils.getIdEmpresaActual();
            return sR.findByEmpresa(idEmpresa);
        }

        // Si es ADMIN, ve el catálogo completo de streamers
        return sR.findAll();    }

    @Override
    public Streamer insert(Streamer streamer) {
        return sR.save(streamer);
    }

    @Override
    public Optional<Streamer> listId(int id) {
        return sR.findById(id);
    }

    @Override
    public Streamer update(Streamer streamer) {
        return sR.save(streamer);
    }

    @Override
    public void delete(int id) {
        sR.deleteById(id);
    }

    @Override
    public List<Streamer> listByEmpresa(int idEmpresa) {
        return sR.findByEmpresa(idEmpresa);    }
}
