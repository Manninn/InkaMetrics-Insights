package pe.edu.upc.inkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics.entities.Transmisiones;

@Repository
public interface ITransmisionesRepository extends JpaRepository<Transmisiones,Integer> {
}
