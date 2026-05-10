package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Region;

import java.util.List;

@Repository
public interface IRegionRepository extends JpaRepository<Region, Integer> {

    @Query("SELECT r FROM Region r WHERE r.Nombre LIKE %:nombre%")
    List<Region> buscarPorNombre(@Param("nombre") String nombre);

}