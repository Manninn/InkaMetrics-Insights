package pe.edu.upc.inkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics.entities.Regiones;

import java.util.List;

@Repository
public interface IRegionesRepository extends JpaRepository<Regiones,Integer> {

    @Query("SELECT r FROM Regiones r WHERE r.nombre LIKE %:nombre%")
    List<Regiones> buscarPorNombre(@Param("nombre") String nombre);
}