package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.MetricSnapshot;

import java.util.List;
import java.util.Optional;

public interface IMetricSnapshotService {
    public List<MetricSnapshot> list();
    public MetricSnapshot insert(MetricSnapshot metricSnapshot);
    public Optional<MetricSnapshot> listId(int id);
    public MetricSnapshot update(MetricSnapshot metricSnapshot);
    public void delete(int id);
    public List<MetricSnapshot> listByCompany(int companyId);

    public List<Object[]> reportMetricsNative(String metricName);
    public List<Object[]> findMetricsPerformanceByRegionNative();
    public List<String> findDistinctNames();
}
