package pe.edu.upc.tpbackinkametrics.serviceinterfaces;


import pe.edu.upc.tpbackinkametrics.entities.Marca;
import pe.edu.upc.tpbackinkametrics.entities.MetricaSnapshot;

import java.util.List;
import java.util.Optional;

public interface IMetricaSnapshotService {
    public List<MetricaSnapshot> list();
    public MetricaSnapshot insert(MetricaSnapshot metricaSnapshot);
    public Optional<MetricaSnapshot> listId(int id);
    public MetricaSnapshot update(MetricaSnapshot metricaSnapshot);
    public void delete(int id);
    public List<MetricaSnapshot> listByEmpresa(int idEmpresa);

    public List<Object[]> reporteMetricasNativo(String nombreMetrica);
    public List<Object[]> findMetricsPerformanceByRegionNativo();
}
