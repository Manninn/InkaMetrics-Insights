package upc.edu.pe.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import upc.edu.pe.tpbackinkametrics.entities.CanalMonitoreado;

import java.util.List;

@Repository
public interface ICanalMonitoreadoRepository extends JpaRepository<CanalMonitoreado,Integer> {
    @Query("SELECT cm FROM CanalMonitoreado cm WHERE cm.Empresa.IdEmpresa = :IdEmpresa")
    List<CanalMonitoreado> findByEmpresa(@Param("IdEmpresa") int IdEmpresa);
}
