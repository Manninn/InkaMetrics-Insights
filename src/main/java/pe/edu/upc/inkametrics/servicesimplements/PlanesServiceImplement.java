package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Planes;
import pe.edu.upc.inkametrics.repositories.IPlanesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlanesService;

import java.util.List;
import java.util.Optional;

@Service
public class PlanesServiceImplement implements IPlanesService {
    @Autowired
    private IPlanesRepository cR;

    @Override
    public List<Planes> list() {
        return cR.findAll();
    }

    @Override
    public Planes insert(Planes p) {
        return cR.save(p);
    }

    @Override
    public Optional<Planes> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Planes pla) {
        cR.save(pla);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
