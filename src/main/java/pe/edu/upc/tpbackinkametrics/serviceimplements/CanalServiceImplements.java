package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Canal;
import pe.edu.upc.tpbackinkametrics.repositories.ICanalRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.ICanalService;
import pe.edu.upc.tpbackinkametrics.util.SecurityUtils;

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

    @Override
    public List<Object[]> contarCanalesPorPlataforma() {
        return cR.contarCanalesPorPlataforma();
    }

    @Override
    public List<Object[]> promedioSeguidoresPorPlataforma() {
        return cR.promedioSeguidoresPorPlataforma();
    }
}
