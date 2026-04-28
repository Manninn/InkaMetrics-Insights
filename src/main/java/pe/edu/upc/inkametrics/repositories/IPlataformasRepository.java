package pe.edu.upc.inkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics.entities.Plataformas;

import java.util.List;

@Repository
public interface IPlataformasRepository extends JpaRepository<Plataformas,Integer> {
    @Query("SELECT p FROM Plataformas p WHERE p.nombre LIKE %:nombre%")
    List<Plataformas> buscarPorNombre(@Param("nombre") String nombre);
}
