package pe.edu.upc.tpbackinkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tpbackinkametrics.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);
}
