package upc.edu.pe.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.tpbackinkametrics.entities.Plan;
import upc.edu.pe.tpbackinkametrics.repositories.IPlanRepository;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IPlanService;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImplements implements IPlanService {

    @Autowired
    private IPlanRepository pR;

    @Override
    public List<Plan> list() {
        return pR.findAll();
    }

    @Override
    public Plan insert(Plan plan) {
        return pR.save(plan);
    }

    @Override
    public Optional<Plan> listId(int id) {
        return pR.findById(id);
    }

    @Override
    public Plan update(Plan plan) {
        return pR.save(plan);
    }

    @Override
    public void delete(int id) {
        pR.deleteById(id);
    }
}
