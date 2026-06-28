package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.MonitoredChannel;
import pe.edu.upc.tpbackinkametrics.repositories.IMonitoredChannelRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IMonitoredChannelService;
import pe.edu.upc.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MonitoredChannelServiceImpl implements IMonitoredChannelService {
    @Autowired
    private IMonitoredChannelRepository monitoredChannelRepository;

    @Override
    public List<MonitoredChannel> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isClient = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (isClient) {
            int companyId = SecurityUtils.getIdEmpresaActual();
            return monitoredChannelRepository.findByCompany(companyId);
        }
        return monitoredChannelRepository.findAll();
    }

    @Override
    public MonitoredChannel insert(MonitoredChannel monitoredChannel) {
        return monitoredChannelRepository.save(monitoredChannel);
    }

    @Override
    public Optional<MonitoredChannel> listId(int id) {
        return monitoredChannelRepository.findById(id);
    }

    @Override
    public MonitoredChannel update(MonitoredChannel monitoredChannel) {
        return monitoredChannelRepository.save(monitoredChannel);
    }

    @Override
    public void delete(int id) {
        monitoredChannelRepository.deleteById(id);
    }

    @Override
    public List<MonitoredChannel> listByCompany(int companyId) {
        return monitoredChannelRepository.findByCompany(companyId);
    }
}
