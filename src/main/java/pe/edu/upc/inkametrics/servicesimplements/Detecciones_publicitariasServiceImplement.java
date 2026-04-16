package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Detecciones_publicitarias;
import pe.edu.upc.inkametrics.repositories.IDetecciones_publicitariasRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IDetecciones_publicitariasService;

import java.util.List;

@Service
public class Detecciones_publicitariasServiceImplement implements IDetecciones_publicitariasService {

    @Autowired
    private IDetecciones_publicitariasRepository cR;

    @Override
    public List<Detecciones_publicitarias> list() {
        return List.of();
    }
}
