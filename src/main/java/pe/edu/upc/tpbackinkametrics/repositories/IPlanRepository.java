package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Plan;

@Repository
public interface IPlanRepository extends JpaRepository<Plan,Integer> {

}
