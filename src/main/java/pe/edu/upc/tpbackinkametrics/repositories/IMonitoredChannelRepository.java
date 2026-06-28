package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.MonitoredChannel;

import java.util.List;

@Repository
public interface IMonitoredChannelRepository extends JpaRepository<MonitoredChannel, Integer> {
    @Query("SELECT mc FROM MonitoredChannel mc WHERE mc.company.id = :companyId")
    List<MonitoredChannel> findByCompany(@Param("companyId") int companyId);
}
