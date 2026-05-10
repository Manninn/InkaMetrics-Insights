package upc.edu.pe.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upc.edu.pe.tpbackinkametrics.entities.CanalMonitoreado;
import upc.edu.pe.tpbackinkametrics.repositories.ICanalMonitoreadoRepository;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.ICanalMonitoreadoService;
import upc.edu.pe.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CanalMonitoreadoServiceImplements implements ICanalMonitoreadoService {
    @Autowired
    private ICanalMonitoreadoRepository cmR;


    @Override
    public List<CanalMonitoreado> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean esCliente = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (esCliente) {
            int idEmpresa = SecurityUtils.getIdEmpresaActual();
            return cmR.findByEmpresa(idEmpresa);
        }

        // El ADMIN ve todas las asignaciones de todos los clientes del sistema
        return cmR.findAll();
    }

    @Override
    public CanalMonitoreado insert(CanalMonitoreado CanalMo) {
        return cmR.save(CanalMo);
    }

    @Override
    public Optional<CanalMonitoreado> listId(int id) {
        return cmR.findById(id);
    }

    @Override
    public CanalMonitoreado update(CanalMonitoreado CanalMo) {
        return cmR.save(CanalMo);
    }

    @Override
    public void delete(int id) {
        cmR.deleteById(id);
    }

    @Override
    public List<CanalMonitoreado> listByEmpresa(int idEmpresa) {
        return cmR.findByEmpresa(idEmpresa);
    }
}
