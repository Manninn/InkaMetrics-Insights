package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Marcas;
import pe.edu.upc.inkametrics.repositories.IMarcasRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IMarcasService;
import tools.jackson.databind.annotation.JsonAppend;

import java.util.List;

@Service
public class MarcasServiceImplement implements IMarcasService {
    @Autowired
    private IMarcasRepository cR;

    @Override
    public List<Marcas> list() {
        return List.of();
    }
}
