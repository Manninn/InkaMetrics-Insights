package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.MetricSnapshot;

import java.util.List;

@Repository
public interface IMetricSnapshotRepository extends JpaRepository<MetricSnapshot, Integer> {
    @Query("SELECT ms FROM MetricSnapshot ms WHERE ms.broadcast.channel.id IN " +
            "(SELECT mc.channel.id FROM MonitoredChannel mc WHERE mc.company.id = :companyId)")
    List<MetricSnapshot> findByCompany(@Param("companyId") int companyId);

    @Query(value = "SELECT b.stream_title, ms.name, SUM(ms.amount) " +
            "FROM metric_snapshot ms " +
            "INNER JOIN broadcast b ON ms.broadcast_id = b.id " +
            "WHERE ms.name = :name " +
            "GROUP BY b.stream_title, ms.name " +
            "ORDER BY SUM(ms.amount) DESC",
            nativeQuery = true)
    List<Object[]> reportMetricsNative(@Param("name") String name);

    @Query(value = "SELECT DISTINCT ms.name FROM metric_snapshot ms ORDER BY ms.name", nativeQuery = true)
    List<String> findDistinctNames();

    @Query(value = "SELECT r.name, ms.name AS metric_name, AVG(ms.amount) " +
            "FROM metric_snapshot ms " +
            "INNER JOIN broadcast b ON ms.broadcast_id = b.id " +
            "INNER JOIN channel c ON b.channel_id = c.id " +
            "INNER JOIN streamer s ON c.streamer_id = s.id " +
            "INNER JOIN region r ON s.region_id = r.id " +
            "GROUP BY r.name, ms.name " +
            "ORDER BY AVG(ms.amount) DESC",
            nativeQuery = true)
    List<Object[]> findMetricsPerformanceByRegionNative();
}
