package upc.edu.pe.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import upc.edu.pe.tpbackinkametrics.entities.Plataforma;

import java.util.List;

@Repository
public interface IPlataformaRepository extends JpaRepository<Plataforma,Integer> {
    @Query("SELECT p FROM Plataforma p WHERE p.Nombre LIKE %:Nombre%")
    List<Plataforma> buscarPorNombre(@Param("Nombre") String Nombre);
}
