package pe.edu.upc.inkametrics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inkametrics.entities.Empresas_clientes;

@Repository
public interface IEmpresas_clientesRepository extends JpaRepository<Empresas_clientes,Integer> {
}
