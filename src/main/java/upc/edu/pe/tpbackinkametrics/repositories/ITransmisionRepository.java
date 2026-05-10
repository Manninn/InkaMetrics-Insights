package upc.edu.pe.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import upc.edu.pe.tpbackinkametrics.entities.Transmision;

import java.util.List;

@Repository
public interface ITransmisionRepository extends JpaRepository<Transmision,Integer> {
    @Query("SELECT t FROM Transmision t WHERE t.canal.IdCanal IN " +
            "(SELECT cm.Canal.IdCanal FROM CanalMonitoreado cm WHERE cm.Empresa.IdEmpresa = :IdEmpresa)")
    List<Transmision> findByEmpresa(@Param("IdEmpresa") int IdEmpresa);
}
