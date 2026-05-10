package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Marca;

import java.util.List;

@Repository
public interface IMarcaRepository extends JpaRepository<Marca,Integer> {
    @Query("SELECT m FROM Marca m WHERE m.Nombre LIKE %:nombre%")
    List<Marca> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT b FROM Marca b WHERE b.Sector = :sector")
    List<Marca> findBySector(@Param("sector") String sector);
}
