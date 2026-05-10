package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Empresa;

import java.util.List;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa,Integer> {

    @Query("SELECT e FROM Empresa e WHERE e.Plan.IdPlan = :idPlan")
    List<Empresa> buscarPorPlan(@Param("idPlan") int idPlan);
}