package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Canales;
import pe.edu.upc.inkametrics.repositories.ICanalesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.ICanalesService;

import java.util.List;
@Service
public class CanalesServiceImplement implements ICanalesService {
    @Autowired
    private ICanalesRepository cR;

    @Override
    public List<Canales> list() {
        return cR.findAll();
    }
}
