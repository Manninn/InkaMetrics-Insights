package pe.edu.upc.inkametrics.servicesimplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Usuarios;
import pe.edu.upc.inkametrics.repositories.IUsuariosRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IUsuariosService;

import java.util.List;
import java.util.Optional;
@Service
public class UsuariosServiceImplement implements IUsuariosService {
    @Autowired
    private IUsuariosRepository cR;
    @Override
    public List<Usuarios> list() {
        return cR.findAll();
    }
    @Override
    public Usuarios insert(Usuarios u) {
        return cR.save(u);
    }
    @Override
    public Optional<Usuarios> listId(int id) {
        return cR.findById(id);
    }
    @Override
    public void update(Usuarios u) {
        cR.save(u);
    }
    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
