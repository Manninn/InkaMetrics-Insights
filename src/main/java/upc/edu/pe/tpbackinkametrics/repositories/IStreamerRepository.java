package upc.edu.pe.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import upc.edu.pe.tpbackinkametrics.entities.Streamer;

import java.util.List;

@Repository
public interface IStreamerRepository extends JpaRepository<Streamer,Integer> {
    @Query("SELECT s FROM Streamer s WHERE s.IdStreamer IN " +
            "(SELECT cm.Canal.Streamer.IdStreamer FROM CanalMonitoreado cm WHERE cm.Empresa.IdEmpresa = :IdEmpresa)")
    List<Streamer> findByEmpresa(@Param("IdEmpresa") int IdEmpresa);
}
