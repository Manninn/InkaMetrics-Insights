package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Transmisiones;
import pe.edu.upc.inkametrics.repositories.ITransmisionesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.ITransmisionesService;

import java.util.List;
import java.util.Optional;

@Service
public class TransmisionesServiceImplement implements ITransmisionesService {
    @Autowired
    private ITransmisionesRepository cR;

    @Override
    public List<Transmisiones> list() {
        return cR.findAll();
    }

    @Override
    public Transmisiones insert(Transmisiones tra) {
        return cR.save(tra);
    }

    @Override
    public Optional<Transmisiones> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Transmisiones tra) {
        cR.save(tra);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
