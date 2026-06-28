package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Brand;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Integer> {
}
