package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.AdDetection;
import pe.edu.upc.tpbackinkametrics.repositories.IAdDetectionRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IAdDetectionService;
import pe.edu.upc.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class AdDetectionServiceImpl implements IAdDetectionService {
    @Autowired
    private IAdDetectionRepository adDetectionRepository;

    @Override
    public List<AdDetection> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isClient = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (isClient) {
            int companyId = SecurityUtils.getIdEmpresaActual();
            return adDetectionRepository.findByCompany(companyId);
        }
        return adDetectionRepository.findAll();
    }

    @Override
    public AdDetection insert(AdDetection adDetection) {
        return adDetectionRepository.save(adDetection);
    }

    @Override
    public Optional<AdDetection> listId(int id) {
        return adDetectionRepository.findById(id);
    }

    @Override
    public AdDetection update(AdDetection adDetection) {
        return adDetectionRepository.save(adDetection);
    }

    @Override
    public void delete(int id) {
        adDetectionRepository.deleteById(id);
    }

    @Override
    public List<AdDetection> listByCompany(int companyId) {
        return adDetectionRepository.findByCompany(companyId);
    }
}
