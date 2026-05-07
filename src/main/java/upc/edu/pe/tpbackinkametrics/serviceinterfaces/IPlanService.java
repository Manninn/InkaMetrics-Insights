package upc.edu.pe.tpbackinkametrics.serviceinterfaces;

import upc.edu.pe.tpbackinkametrics.entities.Plan;

import java.util.List;
import java.util.Optional;

public interface IPlanService {
    public List<Plan> list();
    public Plan insert(Plan plan);
    public Optional<Plan> listId(int id);
    public Plan update(Plan plan);
    public void delete(int id);

}
