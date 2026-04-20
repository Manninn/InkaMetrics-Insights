package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Canales;
import pe.edu.upc.inkametrics.repositories.ICanalesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.ICanalesService;

import java.util.List;
import java.util.Optional;

@Service
public class CanalesServiceImplement implements ICanalesService {
    @Autowired
    private ICanalesRepository cR;

    @Override
    public List<Canales> list() {
        return cR.findAll();
    }

    @Override
    public Canales insert(Canales c) {
        return cR.save(c);
    }

    @Override
    public Optional<Canales> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Canales can) {
        cR.save(can);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
