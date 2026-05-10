package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Plataforma;

@Repository
public interface IPlataformaRepository extends JpaRepository<Plataforma,Integer> {
}
