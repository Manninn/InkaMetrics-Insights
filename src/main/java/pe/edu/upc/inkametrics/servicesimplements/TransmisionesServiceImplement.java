package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Transmisiones;
import pe.edu.upc.inkametrics.repositories.ITransmisionesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.ITransmisionesService;

import java.util.List;

@Service
public class TransmisionesServiceImplement implements ITransmisionesService {
    @Autowired
    private ITransmisionesRepository cR;

    @Override
    public List<Transmisiones> list() {
        return List.of();
    }
}
