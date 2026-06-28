package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Streamer;

import java.util.List;

@Repository
public interface IStreamerRepository extends JpaRepository<Streamer, Integer> {
    @Query("SELECT s FROM Streamer s WHERE s.id IN " +
            "(SELECT mc.channel.streamer.id FROM MonitoredChannel mc WHERE mc.company.id = :companyId)")
    List<Streamer> findByCompany(@Param("companyId") int companyId);
}
