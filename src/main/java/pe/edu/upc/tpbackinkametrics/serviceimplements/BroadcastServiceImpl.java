package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Broadcast;
import pe.edu.upc.tpbackinkametrics.repositories.IBroadcastRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IBroadcastService;
import pe.edu.upc.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BroadcastServiceImpl implements IBroadcastService {
    @Autowired
    private IBroadcastRepository broadcastRepository;

    @Override
    public List<Broadcast> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isClient = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (isClient) {
            int companyId = SecurityUtils.getIdEmpresaActual();
            return broadcastRepository.findByCompany(companyId);
        }
        return broadcastRepository.findAll();
    }

    @Override
    public Broadcast insert(Broadcast broadcast) {
        return broadcastRepository.save(broadcast);
    }

    @Override
    public Optional<Broadcast> listId(int id) {
        return broadcastRepository.findById(id);
    }

    @Override
    public Broadcast update(Broadcast broadcast) {
        return broadcastRepository.save(broadcast);
    }

    @Override
    public void delete(int id) {
        broadcastRepository.deleteById(id);
    }

    @Override
    public List<Broadcast> listByCompany(int companyId) {
        return broadcastRepository.findByCompany(companyId);
    }
}
