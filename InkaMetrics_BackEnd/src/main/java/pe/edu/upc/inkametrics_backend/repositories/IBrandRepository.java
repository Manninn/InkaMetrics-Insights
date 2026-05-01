package pe.edu.upc.inkametrics_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics_backend.entities.Brand;

import java.util.List;

@Repository
public interface IBrandRepository extends JpaRepository<Brand,Integer> {
    @Query("SELECT b FROM Brand b WHERE b.sectorBrand = :sector")
    List<Brand> findBySector(@Param("sector") String sector);


}
