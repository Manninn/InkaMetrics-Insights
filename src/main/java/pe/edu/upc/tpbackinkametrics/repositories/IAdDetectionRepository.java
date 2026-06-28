package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.AdDetection;

import java.util.List;

@Repository
public interface IAdDetectionRepository extends JpaRepository<AdDetection, Integer> {
    @Query("SELECT d FROM AdDetection d WHERE d.broadcast.channel.id IN " +
            "(SELECT mc.channel.id FROM MonitoredChannel mc WHERE mc.company.id = :companyId)")
    List<AdDetection> findByCompany(@Param("companyId") int companyId);
}
