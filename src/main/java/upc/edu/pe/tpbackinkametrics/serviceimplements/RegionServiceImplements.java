package upc.edu.pe.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.tpbackinkametrics.entities.Region;
import upc.edu.pe.tpbackinkametrics.repositories.IPlanRepository;
import upc.edu.pe.tpbackinkametrics.repositories.IRegionRepository;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IRegionService;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImplements implements IRegionService {
    @Autowired
    private IRegionRepository rR;

    @Override
    public List<Region> list() {
        return rR.findAll();
    }

    @Override
    public Region insert(Region region) {
        return rR.save(region);
    }

    @Override
    public Optional<Region> listId(int id) {
        return rR.findById(id);
    }

    @Override
    public Region update(Region region) {
        return rR.save(region);
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }
}
