package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Regiones;
import pe.edu.upc.inkametrics.repositories.IRegionesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IRegionesService;

import java.util.List;

@Service
public class RegionesServiceImplement implements IRegionesService {
    @Autowired
    private IRegionesRepository cR;

    @Override
    public List<Regiones> list() {
        return List.of();
    }
}
