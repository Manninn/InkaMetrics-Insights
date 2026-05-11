package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Transmision;

import java.util.List;

@Repository
public interface ITransmisionRepository extends JpaRepository<Transmision,Integer> {
    @Query("SELECT t FROM Transmision t WHERE t.canal.IdCanal IN " +
            "(SELECT cm.Canal.IdCanal FROM CanalMonitoreado cm WHERE cm.Empresa.IdEmpresa = :IdEmpresa)")
    List<Transmision> findByEmpresa(@Param("IdEmpresa") int IdEmpresa);

    @Query(value = "SELECT s.nick_name, COUNT(t.id_transmision) " + // <-- Cambiado aquí
            "FROM transmision t " +
            "JOIN canal c ON t.id_canal = c.id_canal " +
            "JOIN streamer s ON c.id_streamer = s.id_streamer " +
            "JOIN canal_monitoreado cm ON c.id_canal = cm.id_canal " +
            "WHERE cm.id_empresa = :idEmpresa " +
            "GROUP BY s.nick_name", nativeQuery = true) // <-- Y aquí también
    List<String[]> countTransmissionsByStreamer(@Param("idEmpresa") int idEmpresa);
}
