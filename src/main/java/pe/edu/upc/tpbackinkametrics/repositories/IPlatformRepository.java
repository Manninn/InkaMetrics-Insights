package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.Platform;

@Repository
public interface IPlatformRepository extends JpaRepository<Platform, Integer> {
}
