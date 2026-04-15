package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Roles;
import pe.edu.upc.inkametrics.repositories.IRolesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IRolesService;

import java.util.List;

@Service
public class RolesServiceImplement implements IRolesService {
    @Autowired
    private IRolesRepository cR;

    @Override
    public List<Roles> list() {
        return List.of();
    }
}
