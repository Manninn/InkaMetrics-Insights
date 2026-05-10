package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Plataforma;
import pe.edu.upc.tpbackinkametrics.repositories.IPlanRepository;
import pe.edu.upc.tpbackinkametrics.repositories.IPlataformaRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IPlataformaService;

import java.util.List;
import java.util.Optional;

@Service
public class PlataformaServiceImplements implements IPlataformaService {
    @Autowired
    private IPlataformaRepository plR;

    @Override
    public List<Plataforma> list() {
        return plR.findAll();
    }

    @Override
    public Plataforma insert(Plataforma plataforma) {
        return plR.save(plataforma);
    }

    @Override
    public Optional<Plataforma> listId(int id) {
        return plR.findById(id);
    }

    @Override
    public Plataforma update(Plataforma plataforma) {
        return plR.save(plataforma);
    }

    @Override
    public void delete(int id) {
        plR.deleteById(id);
    }
}
