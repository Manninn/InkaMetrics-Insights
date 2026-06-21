package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.MetricaSnapshot;

import java.util.List;

@Repository
public interface IMetricaSnapshotRepository extends JpaRepository<MetricaSnapshot,Integer> {
    @Query("SELECT ms FROM MetricaSnapshot ms WHERE ms.Transmision.canal.IdCanal IN " +
            "(SELECT cm.Canal.IdCanal FROM CanalMonitoreado cm WHERE cm.Empresa.IdEmpresa = :IdEmpresa)")
    List<MetricaSnapshot> findByEmpresa(@Param("IdEmpresa") int IdEmpresa);


    @Query(value = "SELECT t.titulo_stream, m.nombre, SUM(m.cantidad) " +
            "FROM metrica_snapshot m " +
            "INNER JOIN transmision t ON m.id_transmision = t.id_transmision " +
            "WHERE m.nombre = :nombre " +
            "GROUP BY t.titulo_stream, m.nombre " +
            "ORDER BY SUM(m.cantidad) DESC",
            nativeQuery = true)
    List<Object[]> reporteMetricasNativo(@Param("nombre") String nombre);

    @Query(value = "SELECT r.nombre, m.nombre AS nombre_metrica, AVG(m.cantidad) " +
            "FROM metrica_snapshot m " +
            "INNER JOIN transmision t ON m.id_transmision = t.id_transmision " +
            "INNER JOIN canal c ON t.id_canal = c.id_canal " +
            "INNER JOIN streamer s ON c.id_streamer = s.id_streamer " +
            "INNER JOIN region r ON s.id_region = r.id_region " +
            "GROUP BY r.nombre, m.nombre " +
            "ORDER BY AVG(m.cantidad) DESC",
            nativeQuery = true)
    List<Object[]> findMetricsPerformanceByRegionNativo();
}
