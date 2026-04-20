package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Regiones;
import pe.edu.upc.inkametrics.repositories.IRegionesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IRegionesService;

import java.util.List;
import java.util.Optional;

@Service
public class RegionesServiceImplement implements IRegionesService {
    @Autowired
    private IRegionesRepository cR;

    @Override
    public List<Regiones> list() {
        return cR.findAll();
    }

    @Override
    public Regiones insert(Regiones r) {
        return cR.save(r);
    }

    @Override
    public Optional<Regiones> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Regiones reg) {
        cR.save(reg);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
