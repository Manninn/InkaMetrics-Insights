package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Canales;
import pe.edu.upc.inkametrics.entities.Plataformas;
import pe.edu.upc.inkametrics.repositories.ICanalesRepository;
import pe.edu.upc.inkametrics.repositories.IPlanesRepository;
import pe.edu.upc.inkametrics.repositories.IPlataformasRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.ICanalesService;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlataformasService;

import java.util.List;
import java.util.Optional;

@Service
public class CanalesServiceImplement implements ICanalesService {
    @Autowired
    private ICanalesRepository cR;
    @Autowired
    private IPlataformasRepository pR;

    @Override
    public List<Canales> list() {
        return cR.findAll();
    }

    @Override
    public Canales insert(Canales canales) {
        return cR.save(canales);
    }

    @Override
    public Canales update(Canales canales) {
        return cR.save(canales);
    }

    @Override
    public Optional<Canales> findById(Integer id) {
        return cR.findById(id);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }

    @Override
    public List<Canales> findByPlataformaId(Integer id) {
        return cR.findByPlataformaIdplataforma(id);
    }

    @Override
    public Canales insertPlataformaIdAndCanale(Integer id, Canales canale) {
        Optional<Plataformas> plataforma = pR.findById(id);

        if (plataforma.isPresent()) {
            canale.setPlataforma(plataforma.get());
            return cR.save(canale);
        }
        return null;
    }
}
