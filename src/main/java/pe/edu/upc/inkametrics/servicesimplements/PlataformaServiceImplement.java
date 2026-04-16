package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Plataformas;
import pe.edu.upc.inkametrics.repositories.IPlataformasRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlataformasService;

import java.util.List;

@Service
public class PlataformaServiceImplement implements IPlataformasService {
    @Autowired
    private IPlataformasRepository cR;

    @Override
    public List<Plataformas> list() {
        return List.of();
    }
}
