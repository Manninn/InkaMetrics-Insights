package pe.edu.upc.inkametrics_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics_backend.entities.DetectionAdvertising;

import java.util.List;

@Repository
public interface IDetectionAdvertisingRepository extends JpaRepository<DetectionAdvertising,Integer> {
    @Query(value = "SELECT b.brand_name as marca, COUNT(d.id_detection_advertising) as cantidad " +
            "FROM brand b JOIN detectionadvertising d ON b.id_brand = d.id_brand " +
            "GROUP BY b.brand_name", nativeQuery = true)
    List<String[]> countDetectionsByBrand();

    @Query("SELECT d.typeTransmission, SUM(d.durationsegTransmission) " +
            "FROM DetectionAdvertising d " +
            "GROUP BY d.typeTransmission")
    List<String[]> sumDurationByType();


}
