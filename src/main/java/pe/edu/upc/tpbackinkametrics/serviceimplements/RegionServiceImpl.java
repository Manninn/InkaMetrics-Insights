package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Region;
import pe.edu.upc.tpbackinkametrics.repositories.IRegionRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IRegionService;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private IRegionRepository regionRepository;

    @Override
    public List<Region> list() {
        return regionRepository.findAll();
    }

    @Override
    public Region insert(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public Optional<Region> listId(int id) {
        return regionRepository.findById(id);
    }

    @Override
    public Region update(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public void delete(int id) {
        regionRepository.deleteById(id);
    }

    @Override
    public List<Region> findByName(String name) {
        return regionRepository.findByName(name);
    }
}
