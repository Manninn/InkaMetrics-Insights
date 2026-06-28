package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.Region;

import java.util.List;
import java.util.Optional;

public interface IRegionService {
    public List<Region> list();
    public Region insert(Region region);
    public Optional<Region> listId(int id);
    public Region update(Region region);
    public void delete(int id);

    public List<Region> findByName(String name);
}
