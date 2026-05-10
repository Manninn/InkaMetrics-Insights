package upc.edu.pe.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.tpbackinkametrics.entities.Region;

@Repository
public interface IRegionRepository extends JpaRepository <Region,Integer> {
}
