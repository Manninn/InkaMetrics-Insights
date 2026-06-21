package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Transmision;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITransmisionRepository extends JpaRepository<Transmision,Integer> {
    Optional<Transmision> findFirstByCanal_IdCanalAndEnVivoTrue(int idCanal);
    @Query("SELECT t FROM Transmision t WHERE t.canal.IdCanal IN " +
            "(SELECT cm.Canal.IdCanal FROM CanalMonitoreado cm WHERE cm.Empresa.IdEmpresa = :IdEmpresa)")
    List<Transmision> findByEmpresa(@Param("IdEmpresa") int IdEmpresa);
}
