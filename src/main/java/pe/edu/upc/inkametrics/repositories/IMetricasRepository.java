package pe.edu.upc.inkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics.entities.Metricas;

import java.util.List;

@Repository
public interface IMetricasRepository extends JpaRepository<Metricas, Integer> {

    @Query("SELECT m.transmisiones.titulo_stream, SUM(m.cantidadMetrica) " +
            "FROM Metricas m " +
            "WHERE m.nameMetrica = :nombre " +
            "GROUP BY m.transmisiones.titulo_stream " +
            "ORDER BY SUM(m.cantidadMetrica) DESC")
    List<Object[]> reporteMetricasJPQL(@Param("nombre") String nombre);
}
