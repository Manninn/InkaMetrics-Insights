package pe.edu.upc.inkametrics_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics_backend.entities.Streamer;

@Repository
public interface IStreamerRepository extends JpaRepository<Streamer, Integer> {
}
