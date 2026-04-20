package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Roles;
import pe.edu.upc.inkametrics.repositories.IRolesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IRolesService;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImplement implements IRolesService {
    @Autowired
    private IRolesRepository cR;

    @Override
    public List<Roles> list() {
        return cR.findAll();
    }

    @Override
    public Roles insert(Roles r) {
        return cR.save(r);
    }

    @Override
    public Optional<Roles> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Roles rol) {
        cR.save(rol);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
