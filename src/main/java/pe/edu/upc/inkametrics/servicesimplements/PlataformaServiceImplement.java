package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Plataformas;
import pe.edu.upc.inkametrics.repositories.IPlataformasRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlataformasService;

import java.util.List;
import java.util.Optional;

@Service
public class PlataformaServiceImplement implements IPlataformasService {
    @Autowired
    private IPlataformasRepository cR;

    @Override
    public List<Plataformas> list() {
        return cR.findAll();
    }

    @Override
    public Plataformas insert(Plataformas p) {
        return cR.save(p);
    }

    @Override
    public Optional<Plataformas> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Plataformas pla) {
        cR.save(pla);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
