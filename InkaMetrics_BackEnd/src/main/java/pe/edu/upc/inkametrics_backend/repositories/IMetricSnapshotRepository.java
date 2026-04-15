package pe.edu.upc.inkametrics_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics_backend.entities.MetricSnapshot;

@Repository
public interface IMetricSnapshotRepository extends JpaRepository<MetricSnapshot, Integer> {
}
