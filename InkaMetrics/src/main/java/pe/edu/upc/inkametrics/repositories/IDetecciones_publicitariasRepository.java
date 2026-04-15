package pe.edu.upc.inkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics.entities.Detecciones_publicitarias;

@Repository
public interface IDetecciones_publicitariasRepository extends JpaRepository<Detecciones_publicitarias,Integer> {
}
