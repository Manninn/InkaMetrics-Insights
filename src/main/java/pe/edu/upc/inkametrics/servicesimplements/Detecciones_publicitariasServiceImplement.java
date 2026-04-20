package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Detecciones_publicitarias;
import pe.edu.upc.inkametrics.repositories.IDetecciones_publicitariasRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IDetecciones_publicitariasService;

import java.util.List;
import java.util.Optional;

@Service
public class Detecciones_publicitariasServiceImplement implements IDetecciones_publicitariasService {

    @Autowired
    private IDetecciones_publicitariasRepository cR;

    @Override
    public List<Detecciones_publicitarias> list() {
        return cR.findAll();
    }

    @Override
    public Detecciones_publicitarias insert(Detecciones_publicitarias d) {
        return cR.save(d);
    }

    @Override
    public Optional<Detecciones_publicitarias> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Detecciones_publicitarias det) {
        cR.save(det);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
