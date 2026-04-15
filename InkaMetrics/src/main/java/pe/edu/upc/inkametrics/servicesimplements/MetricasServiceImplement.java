package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Metricas;
import pe.edu.upc.inkametrics.repositories.IMetricasRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IMetricasService;

import java.util.List;

@Service
public class MetricasServiceImplement implements IMetricasService {
    @Autowired
    private IMetricasRepository cR;

    @Override
    public List<Metricas> list() {
        return List.of();
    }
}
