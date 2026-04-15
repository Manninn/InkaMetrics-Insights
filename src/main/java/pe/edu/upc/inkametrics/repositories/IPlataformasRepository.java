package pe.edu.upc.inkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics.entities.Plataformas;

@Repository
public interface IPlataformasRepository extends JpaRepository<Plataformas,Integer> {
}
