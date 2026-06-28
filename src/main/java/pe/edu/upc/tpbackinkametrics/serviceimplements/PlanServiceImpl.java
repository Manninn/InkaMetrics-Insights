package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Plan;
import pe.edu.upc.tpbackinkametrics.repositories.IPlanRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IPlanService;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements IPlanService {

    @Autowired
    private IPlanRepository planRepository;

    @Override
    public List<Plan> list() {
        return planRepository.findAll();
    }

    @Override
    public Plan insert(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public Optional<Plan> listId(int id) {
        return planRepository.findById(id);
    }

    @Override
    public Plan update(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public void delete(int id) {
        planRepository.deleteById(id);
    }
}
