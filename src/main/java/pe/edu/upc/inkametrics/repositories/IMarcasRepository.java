package pe.edu.upc.inkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics.entities.Marcas;

import java.util.List;

@Repository
public interface IMarcasRepository extends JpaRepository<Marcas,Integer> {

    @Query("SELECT m FROM Marcas m WHERE m.nombre LIKE %:nombre%")
    List<Marcas> buscarPorNombre(@Param("nombre") String nombre);
}