package pe.edu.upc.inkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics.entities.Canales;

import java.util.List;

@Repository
public interface ICanalesRepository extends JpaRepository<Canales,Integer> {
    List<Canales>findByPlataforma_IdPlataforma(int idPlataforma);

    @Query("SELECT c FROM Canales c WHERE c.seguidores_actuales > :min")
    List<Canales> buscarPorSeguidores(@Param("min") int min);

    @Query("SELECT c.plataforma.nombre, COUNT(c) " +
            "FROM Canales c GROUP BY c.plataforma.nombre")
    List<Object[]> contarCanalesPorPlataforma();

    @Query("SELECT c.plataforma.nombre, AVG(c.seguidores_actuales) " +
            "FROM Canales c GROUP BY c.plataforma.nombre")
    List<Object[]> promedioSeguidoresPorPlataforma();
}
