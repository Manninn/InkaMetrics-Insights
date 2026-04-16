package pe.edu.upc.inkametrics_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics_backend.entities.Transmission;

@Repository
public interface ITransmissionRepository extends JpaRepository<Transmission,Integer> {
}
