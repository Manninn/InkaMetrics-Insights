package pe.edu.upc.inkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics.entities.Canales;
import java.util.List;

@Repository
public interface ICanalesRepository extends JpaRepository<Canales,Integer> {
    List<Canales>findByPlataformaIdplataforma(Integer idPlataforma);
}
