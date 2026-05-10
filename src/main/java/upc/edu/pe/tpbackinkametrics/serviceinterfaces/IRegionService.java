package upc.edu.pe.tpbackinkametrics.serviceinterfaces;

import upc.edu.pe.tpbackinkametrics.entities.Plan;
import upc.edu.pe.tpbackinkametrics.entities.Region;

import java.util.List;
import java.util.Optional;

public interface IRegionService {
    public List<Region> list();
    public Region insert(Region region);
    public Optional<Region> listId(int id);
    public Region update(Region region);
    public void delete(int id);
}
