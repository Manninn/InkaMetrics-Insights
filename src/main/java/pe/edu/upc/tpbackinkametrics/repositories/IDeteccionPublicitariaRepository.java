package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.DeteccionPublicitaria;

import java.util.List;

@Repository
public interface IDeteccionPublicitariaRepository extends JpaRepository<DeteccionPublicitaria,Integer> {
    @Query("SELECT d FROM DeteccionPublicitaria d WHERE d.Transmision.canal.IdCanal IN " +
            "(SELECT cm.Canal.IdCanal FROM CanalMonitoreado cm WHERE cm.Empresa.IdEmpresa = :IdEmpresa)")
    List<DeteccionPublicitaria> findByEmpresa(@Param("IdEmpresa") int IdEmpresa);

    @Query(value = "SELECT b.Nombre as marca, COUNT(d.IdDeteccionPublicitaria) as cantidad " +
            "FROM marca b JOIN deteccion_publicitaria d ON b.IdMarca = d.IdMarca " +
            "GROUP BY b.Nombre", nativeQuery = true)
    List<String[]> countDeteccionMarca();

    @Query("SELECT d.Tipo, SUM(d.TiempoAparicionSeg) FROM DeteccionPublicitaria d GROUP BY d.Tipo")
    List<String[]> sumDurationByTipoPublicidad();
}
