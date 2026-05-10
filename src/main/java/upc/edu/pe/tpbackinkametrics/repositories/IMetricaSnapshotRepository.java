package upc.edu.pe.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import upc.edu.pe.tpbackinkametrics.dtos.MetricaPorRegionDTO;
import upc.edu.pe.tpbackinkametrics.entities.MetricaSnapshot;

import java.util.List;

@Repository
public interface IMetricaSnapshotRepository extends JpaRepository<MetricaSnapshot,Integer> {
    @Query("SELECT ms FROM MetricaSnapshot ms WHERE ms.Transmision.canal.IdCanal IN " +
            "(SELECT cm.Canal.IdCanal FROM CanalMonitoreado cm WHERE cm.Empresa.IdEmpresa = :IdEmpresa)")
    List<MetricaSnapshot> findByEmpresa(@Param("IdEmpresa") int IdEmpresa);
    // query
    // Query 1: Top transmisiones por métrica específica
    @Query("SELECT m.Transmision.TituloStream, SUM(m.Cantidad) " +
            "FROM MetricaSnapshot m " +
            "WHERE m.Nombre = :nombre " +
            "GROUP BY m.Transmision.TituloStream " +
            "ORDER BY SUM(m.Cantidad) DESC")
    List<Object[]> reporteMetricasJPQL(@Param("nombre") String nombre);

    // Query 2: Rendimiento por Región
    @Query("SELECT new upc.edu.pe.tpbackinkametrics.dtos.MetricaPorRegionDTO(r.Nombre, m.Nombre, AVG(m.Cantidad)) " +
            "FROM MetricaSnapshot m " +
            "JOIN m.Transmision t " +
            "JOIN t.canal c " +
            "JOIN c.Streamer s " +
            "JOIN s.region r " +
            "GROUP BY r.Nombre, m.Nombre " +
            "ORDER BY AVG(m.Cantidad) DESC")
    List<MetricaPorRegionDTO> findMetricsPerformanceByRegion();

}
