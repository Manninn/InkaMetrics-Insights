package upc.edu.pe.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upc.edu.pe.tpbackinkametrics.entities.Canal;
import upc.edu.pe.tpbackinkametrics.repositories.ICanalRepository;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.ICanalService;
import upc.edu.pe.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CanalServiceImplements implements ICanalService {
    @Autowired
    private ICanalRepository cR;


    @Override
    public List<Canal> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean esCliente = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (esCliente) {
            // Obtenemos el ID de la empresa del token firmado
            int idEmpresa = SecurityUtils.getIdEmpresaActual();
            return cR.findByEmpresa(idEmpresa);
        }

        // El ADMIN ve todos los canales de todas las plataformas
        return cR.findAll();    }

    @Override
    public Canal insert(Canal canal) {
        return cR.save(canal);
    }

    @Override
    public Optional<Canal> listId(int id) {
        return cR.findById(id);
    }

    @Override
    public Canal update(Canal canal) {
        return cR.save(canal);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }

    @Override
    public List<Canal> listByEmpresa(int idEmpresa) {
        return cR.findByEmpresa(idEmpresa);
    }
}
