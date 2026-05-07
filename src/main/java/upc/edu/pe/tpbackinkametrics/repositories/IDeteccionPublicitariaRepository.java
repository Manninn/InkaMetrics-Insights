package upc.edu.pe.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import upc.edu.pe.tpbackinkametrics.entities.DeteccionPublicitaria;

import java.util.List;

@Repository
public interface IDeteccionPublicitariaRepository extends JpaRepository<DeteccionPublicitaria,Integer> {
    @Query("SELECT d FROM DeteccionPublicitaria d WHERE d.Transmision.canal.IdCanal IN " +
            "(SELECT cm.Canal.IdCanal FROM CanalMonitoreado cm WHERE cm.Empresa.IdEmpresa = :IdEmpresa)")
    List<DeteccionPublicitaria> findByEmpresa(@Param("IdEmpresa") int IdEmpresa);
}
