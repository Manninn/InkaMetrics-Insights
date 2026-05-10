package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Canal;

import java.util.List;

@Repository
public interface ICanalRepository extends JpaRepository<Canal,Integer> {
    @Query("SELECT c FROM Canal c WHERE c.IdCanal IN " +
            "(SELECT cm.Canal.IdCanal FROM CanalMonitoreado cm WHERE cm.Empresa.IdEmpresa = :IdEmpresa)")
    List<Canal> findByEmpresa(@Param("IdEmpresa") int IdEmpresa);

    @Query("SELECT c.Plataforma.Nombre, COUNT(c) FROM Canal c GROUP BY c.Plataforma.Nombre")
    List<Object[]> contarCanalesPorPlataforma();

    @Query("SELECT c.Plataforma.Nombre, AVG(c.SeguidoresActuales) FROM Canal c GROUP BY c.Plataforma.Nombre")
    List<Object[]> promedioSeguidoresPorPlataforma();
}
