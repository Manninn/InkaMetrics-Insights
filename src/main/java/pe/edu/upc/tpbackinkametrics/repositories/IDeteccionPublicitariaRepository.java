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

    @Query(value = "SELECT s.nick_name, " +
            "(SUM(d.tiempo_aparicion_seg) / (NULLIF(c.seguidores_actuales, 0) / 1000.0)) as eficiencia " +
            "FROM deteccion_publicitaria d " +
            "JOIN transmision t ON d.id_transmision = t.id_transmision " +
            "JOIN canal c ON t.id_canal = c.id_canal " +
            "JOIN streamer s ON c.id_streamer = s.id_streamer " +
            "JOIN canal_monitoreado cm ON c.id_canal = cm.id_canal " +
            "WHERE cm.id_empresa = :idEmpresa " +
            "GROUP BY s.nick_name, c.seguidores_actuales " + // Añadimos seguidores_actuales al grupo
            "ORDER BY eficiencia DESC", nativeQuery = true)
    List<String[]> getStreamerEfficiency(@Param("idEmpresa") int idEmpresa);


}
