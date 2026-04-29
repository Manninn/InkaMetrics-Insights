package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Usuarios;
import pe.edu.upc.inkametrics.repositories.IUsuariosRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.IUsuariosService;

import java.util.List;

@Service
public class UsuariosServiceImplement implements IUsuariosService {
    @Autowired
    private IUsuariosRepository cR;

    @Override
    public List<Usuarios> list() {
        return List.of();
    }
}
