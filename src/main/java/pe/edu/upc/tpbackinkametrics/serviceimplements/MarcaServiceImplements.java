package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Marca;
import pe.edu.upc.tpbackinkametrics.repositories.IMarcaRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IMarcaService;
import pe.edu.upc.tpbackinkametrics.util.SecurityUtils;

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

    @Override
    public List<Marca> buscarPorNombre(String nombre) {
        return maR.buscarPorNombre(nombre);
    }

    @Override
    public List<Marca> findBySector(String sector) {
        return maR.findBySector(sector);
    }


}
