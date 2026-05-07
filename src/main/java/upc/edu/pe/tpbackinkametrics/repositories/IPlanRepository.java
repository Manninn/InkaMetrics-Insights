package upc.edu.pe.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.tpbackinkametrics.entities.Plan;

@Repository
public interface IPlanRepository extends JpaRepository<Plan,Integer> {

}
