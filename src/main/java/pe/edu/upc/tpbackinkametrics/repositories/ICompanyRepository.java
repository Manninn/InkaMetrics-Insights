package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Company;

import java.util.List;

@Repository
public interface ICompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT c FROM Company c WHERE c.plan.id = :planId")
    List<Company> findByPlan(@Param("planId") int planId);
}
