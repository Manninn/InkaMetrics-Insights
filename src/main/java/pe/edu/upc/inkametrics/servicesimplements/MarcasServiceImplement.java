package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Marcas;
import pe.edu.upc.inkametrics.repositories.IMarcasRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IMarcasService;

import java.util.List;

@Service
public class MarcasServiceImplement implements IMarcasService {
    @Autowired
    private IMarcasRepository cR;

    @Override
    public void insert(Marcas marcas) {
        cR.save(marcas);
    }

    @Override
    public List<Marcas> list() {
        return cR.findAll();
    }

    @Override
    public void delete(int id_marca) {
        cR.deleteById(id_marca);
    }

    @Override
    public Marcas listId(int id_marca) {
        return cR.findById(id_marca).orElse(new Marcas());
    }
}