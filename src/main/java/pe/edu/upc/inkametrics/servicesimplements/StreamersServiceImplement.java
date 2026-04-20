package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Streamers;
import pe.edu.upc.inkametrics.repositories.IStreamersRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IStreamersService;

import java.util.List;
import java.util.Optional;

@Service
public class StreamersServiceImplement implements IStreamersService {
    @Autowired
    private IStreamersRepository cR;

    @Override
    public List<Streamers> list() {
        return cR.findAll();
    }

    @Override
    public Streamers insert(Streamers s) {
        return cR.save(s);
    }

    @Override
    public Optional<Streamers> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Streamers str) {
        cR.save(str);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
