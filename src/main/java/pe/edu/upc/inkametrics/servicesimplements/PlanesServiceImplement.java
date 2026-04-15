package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Planes;
import pe.edu.upc.inkametrics.repositories.IPlanesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlanesService;

import java.util.List;

@Service
public class PlanesServiceImplement implements IPlanesService {
    @Autowired
    private IPlanesRepository cR;

    @Override
    public List<Planes> list() {
        return List.of();
    }
}
