package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Streamers;
import pe.edu.upc.inkametrics.repositories.IStreamersRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IStreamersService;

import java.util.List;

@Service
public class StreamersServiceImplement implements IStreamersService {
    @Autowired
    private IStreamersRepository cR;

    @Override
    public List<Streamers> list() {
        return List.of();
    }
}
