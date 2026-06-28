package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.MetricSnapshot;
import pe.edu.upc.tpbackinkametrics.repositories.IMetricSnapshotRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IMetricSnapshotService;
import pe.edu.upc.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MetricSnapshotServiceImpl implements IMetricSnapshotService {
    @Autowired
    private IMetricSnapshotRepository metricSnapshotRepository;

    @Override
    public List<MetricSnapshot> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isClient = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (isClient) {
            int companyId = SecurityUtils.getIdEmpresaActual();
            return metricSnapshotRepository.findByCompany(companyId);
        }
        return metricSnapshotRepository.findAll();
    }

    @Override
    public MetricSnapshot insert(MetricSnapshot metricSnapshot) {
        return metricSnapshotRepository.save(metricSnapshot);
    }

    @Override
    public Optional<MetricSnapshot> listId(int id) {
        return metricSnapshotRepository.findById(id);
    }

    @Override
    public MetricSnapshot update(MetricSnapshot metricSnapshot) {
        return metricSnapshotRepository.save(metricSnapshot);
    }

    @Override
    public void delete(int id) {
        metricSnapshotRepository.deleteById(id);
    }

    @Override
    public List<MetricSnapshot> listByCompany(int companyId) {
        return metricSnapshotRepository.findByCompany(companyId);
    }

    @Override
    public List<Object[]> reportMetricsNative(String metricName) {
        return metricSnapshotRepository.reportMetricsNative(metricName);
    }

    @Override
    public List<Object[]> findMetricsPerformanceByRegionNative() {
        return metricSnapshotRepository.findMetricsPerformanceByRegionNative();
    }

    @Override
    public List<String> findDistinctNames() {
        return metricSnapshotRepository.findDistinctNames();
    }
}
