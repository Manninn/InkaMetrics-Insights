package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.tpbackinkametrics.entities.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
