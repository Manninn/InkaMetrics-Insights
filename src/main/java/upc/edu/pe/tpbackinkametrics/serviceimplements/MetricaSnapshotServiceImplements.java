package upc.edu.pe.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upc.edu.pe.tpbackinkametrics.dtos.MetricaPorRegionDTO;
import upc.edu.pe.tpbackinkametrics.entities.MetricaSnapshot;
import upc.edu.pe.tpbackinkametrics.repositories.IMetricaSnapshotRepository;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IMetricaSnapshotService;
import upc.edu.pe.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MetricaSnapshotServiceImplements implements IMetricaSnapshotService {
    @Autowired
    private IMetricaSnapshotRepository mR;

    @Override
    public List<MetricaSnapshot> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean esCliente = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (esCliente) {
            int idEmpresa = SecurityUtils.getIdEmpresaActual();
            return mR.findByEmpresa(idEmpresa);
        }

        // El ADMIN puede ver el historial de todas las transmisiones del sistema
        return mR.findAll();       }

    @Override
    public MetricaSnapshot insert(MetricaSnapshot metricaSnapshot) {
        return mR.save(metricaSnapshot);
    }

    @Override
    public Optional<MetricaSnapshot> listId(int id) {
        return mR.findById(id);
    }

    @Override
    public MetricaSnapshot update(MetricaSnapshot metricaSnapshot) {
        return mR.save(metricaSnapshot);
    }

    @Override
    public void delete(int id) {
        mR.deleteById(id);
    }

    @Override
    public List<MetricaSnapshot> listByEmpresa(int idEmpresa) {
        return mR.findByEmpresa(idEmpresa);
    }

    @Override
    public List<Object[]> reporteMetricasJPQL() {
        return mR.reporteMetricasJPQL("comentarios"); // O el nombre que necesites por defecto
    }

    @Override
    public List<MetricaPorRegionDTO> obtenerRendimientoPorRegion() {
        return mR.findMetricsPerformanceByRegion();
    }
}
