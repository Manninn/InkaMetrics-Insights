package pe.edu.upc.inkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics.dtos.MetricasPorTransmisionDTO;
import pe.edu.upc.inkametrics.entities.Transmisiones;

import java.util.List;

@Repository
public interface ITransmisionesRepository extends JpaRepository<Transmisiones,Integer> {



}
