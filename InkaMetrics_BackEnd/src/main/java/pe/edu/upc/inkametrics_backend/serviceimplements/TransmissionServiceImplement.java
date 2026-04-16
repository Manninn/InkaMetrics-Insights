package pe.edu.upc.inkametrics_backend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics_backend.entities.Transmission;
import pe.edu.upc.inkametrics_backend.repositories.ITransmissionRepository;
import pe.edu.upc.inkametrics_backend.serviceinterfaces.ITransmissionService;

import java.util.List;
import java.util.Optional;

@Service
public class TransmissionServiceImplement implements ITransmissionService {

    @Autowired
    private ITransmissionRepository tS;


    @Override
    public List<Transmission> list() {
        return tS.findAll();
    }

    @Override
    public Transmission insert(Transmission t) {
        return tS.save(t);
    }

    @Override
    public Optional<Transmission> listId(int idTransmission) {
        return tS.findById(idTransmission);
    }

    @Override
    public void update(Transmission t) {tS.save(t);

    }

    @Override
    public void delete(int idTransmission) {
    tS.deleteById(idTransmission);
    }
}

