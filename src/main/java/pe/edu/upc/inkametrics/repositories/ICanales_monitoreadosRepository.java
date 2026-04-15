package pe.edu.upc.inkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics.entities.Canales_monitoreados;

@Repository
public interface ICanales_monitoreadosRepository extends JpaRepository<Canales_monitoreados,Integer> { //id
}
