package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Metricas;
import pe.edu.upc.inkametrics.repositories.IMetricasRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IMetricasService;

import java.util.List;
import java.util.Optional;

@Service
public class MetricasServiceImplement implements IMetricasService {
    @Autowired
    private IMetricasRepository cR;

    @Override
    public List<Metricas> list() {
        return cR.findAll();
    }

    @Override
    public Metricas insert(Metricas m) {
        return cR.save(m);
    }

    @Override
    public Optional<Metricas> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Metricas met) {
        cR.save(met);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
