package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Streamer;
import pe.edu.upc.tpbackinkametrics.repositories.IStreamerRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IStreamerService;
import pe.edu.upc.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class StreamerServiceImpl implements IStreamerService {
    @Autowired
    private IStreamerRepository streamerRepository;

    @Override
    public List<Streamer> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isClient = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (isClient) {
            int companyId = SecurityUtils.getIdEmpresaActual();
            return streamerRepository.findByCompany(companyId);
        }
        return streamerRepository.findAll();
    }

    @Override
    public Streamer insert(Streamer streamer) {
        return streamerRepository.save(streamer);
    }

    @Override
    public Optional<Streamer> listId(int id) {
        return streamerRepository.findById(id);
    }

    @Override
    public Streamer update(Streamer streamer) {
        return streamerRepository.save(streamer);
    }

    @Override
    public void delete(int id) {
        streamerRepository.deleteById(id);
    }

    @Override
    public List<Streamer> listByCompany(int companyId) {
        return streamerRepository.findByCompany(companyId);
    }
}
