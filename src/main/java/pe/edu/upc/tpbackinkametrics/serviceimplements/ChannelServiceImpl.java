package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Channel;
import pe.edu.upc.tpbackinkametrics.repositories.IChannelRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IChannelService;
import pe.edu.upc.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelServiceImpl implements IChannelService {
    @Autowired
    private IChannelRepository channelRepository;

    @Override
    public List<Channel> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isClient = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (isClient) {
            int companyId = SecurityUtils.getIdEmpresaActual();
            return channelRepository.findByCompany(companyId);
        }
        return channelRepository.findAll();
    }

    @Override
    public Channel insert(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public Optional<Channel> listId(int id) {
        return channelRepository.findById(id);
    }

    @Override
    public Channel update(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public void delete(int id) {
        channelRepository.deleteById(id);
    }

    @Override
    public List<Channel> listByCompany(int companyId) {
        return channelRepository.findByCompany(companyId);
    }
}
