package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Marcas;
import pe.edu.upc.inkametrics.repositories.IMarcasRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IMarcasService;
import tools.jackson.databind.annotation.JsonAppend;

import java.util.List;
import java.util.Optional;

@Service
public class MarcasServiceImplement implements IMarcasService {
    @Autowired
    private IMarcasRepository cR;

    @Override
    public List<Marcas> list() {
        return cR.findAll();
    }

    @Override
    public Marcas insert(Marcas m) {
        return cR.save(m);
    }

    @Override
    public Optional<Marcas> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Marcas mar) {
        cR.save(mar);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
