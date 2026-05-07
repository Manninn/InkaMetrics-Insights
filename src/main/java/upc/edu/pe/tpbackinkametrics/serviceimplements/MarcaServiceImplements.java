package upc.edu.pe.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upc.edu.pe.tpbackinkametrics.entities.Marca;
import upc.edu.pe.tpbackinkametrics.repositories.IMarcaRepository;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IMarcaService;
import upc.edu.pe.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImplements implements IMarcaService {
    @Autowired
    private IMarcaRepository maR;


    @Override
    public List<Marca> list() {
        return maR.findAll();    }

    @Override
    public Marca insert(Marca marca) {
        return maR.save(marca);
    }

    @Override
    public Optional<Marca> listId(int id) {
        return maR.findById(id);
    }

    @Override
    public Marca update(Marca marca) {
        return maR.save(marca);
    }

    @Override
    public void delete(int id) {
        maR.deleteById(id);
    }

}
