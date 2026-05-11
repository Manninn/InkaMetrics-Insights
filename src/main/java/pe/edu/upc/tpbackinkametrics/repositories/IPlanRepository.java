package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Plan;

import java.util.List;

import static org.hibernate.grammars.hql.HqlParser.SELECT;
import static org.hibernate.sql.ast.Clause.FROM;

@Repository
public interface IPlanRepository extends JpaRepository<Plan,Integer> {

    @Query("SELECT p FROM Plan p WHERE p.PrecioMensual > 50")
    public List<Plan> listarPlanesMayorPrecio();
}
