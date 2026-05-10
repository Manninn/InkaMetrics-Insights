package upc.edu.pe.tpbackinkametrics.serviceinterfaces;


import upc.edu.pe.tpbackinkametrics.dtos.MetricaPorRegionDTO;
import upc.edu.pe.tpbackinkametrics.entities.Marca;
import upc.edu.pe.tpbackinkametrics.entities.MetricaSnapshot;

import java.util.List;
import java.util.Optional;

public interface IMetricaSnapshotService {
    public List<MetricaSnapshot> list();
    public MetricaSnapshot insert(MetricaSnapshot metricaSnapshot);
    public Optional<MetricaSnapshot> listId(int id);
    public MetricaSnapshot update(MetricaSnapshot metricaSnapshot);
    public void delete(int id);
    public List<MetricaSnapshot> listByEmpresa(int idEmpresa);
    List<Object[]> reporteMetricasJPQL();
    List<MetricaPorRegionDTO> obtenerRendimientoPorRegion();
}
