package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Channel;

import java.util.List;

@Repository
public interface IChannelRepository extends JpaRepository<Channel, Integer> {
    @Query("SELECT c FROM Channel c WHERE c.id IN " +
            "(SELECT mc.channel.id FROM MonitoredChannel mc WHERE mc.company.id = :companyId)")
    List<Channel> findByCompany(@Param("companyId") int companyId);
}
