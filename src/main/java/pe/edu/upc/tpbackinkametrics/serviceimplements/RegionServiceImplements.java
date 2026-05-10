package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Region;
import pe.edu.upc.tpbackinkametrics.repositories.IRegionRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IRegionService;

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

    @Override
    public List<Region> buscarPorNombre(String nombre) {
        return rR.buscarPorNombre(nombre);
    }
}