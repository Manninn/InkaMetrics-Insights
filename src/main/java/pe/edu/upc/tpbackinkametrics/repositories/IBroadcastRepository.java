package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Broadcast;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBroadcastRepository extends JpaRepository<Broadcast, Integer> {
    @Query("SELECT b FROM Broadcast b WHERE b.channel.id = :channelId AND b.isLive = true")
    Optional<Broadcast> findActiveByChannel(@Param("channelId") int channelId);

    @Query("SELECT b FROM Broadcast b WHERE b.channel.id IN " +
            "(SELECT mc.channel.id FROM MonitoredChannel mc WHERE mc.company.id = :companyId)")
    List<Broadcast> findByCompany(@Param("companyId") int companyId);
}
