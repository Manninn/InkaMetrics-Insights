package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Platform;
import pe.edu.upc.tpbackinkametrics.repositories.IPlatformRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IPlatformService;

import java.util.List;
import java.util.Optional;

@Service
public class PlatformServiceImpl implements IPlatformService {
    @Autowired
    private IPlatformRepository platformRepository;

    @Override
    public List<Platform> list() {
        return platformRepository.findAll();
    }

    @Override
    public Platform insert(Platform platform) {
        return platformRepository.save(platform);
    }

    @Override
    public Optional<Platform> listId(int id) {
        return platformRepository.findById(id);
    }

    @Override
    public Platform update(Platform platform) {
        return platformRepository.save(platform);
    }

    @Override
    public void delete(int id) {
        platformRepository.deleteById(id);
    }
}
