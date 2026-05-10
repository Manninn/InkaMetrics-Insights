package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.Empresa;

import java.util.List;
import java.util.Optional;

public interface IEmpresaService {
    public List<Empresa> list();
    public Empresa insert(Empresa empresa);
    public Optional<Empresa> listId(int id);
    public Empresa update(Empresa empresa);
    public void delete(int id);
    public List<Empresa> buscarPorPlan(int idPlan);
}