package upc.edu.pe.tpbackinkametrics.serviceinterfaces;

import upc.edu.pe.tpbackinkametrics.entities.Empresa;
import upc.edu.pe.tpbackinkametrics.entities.Plan;

import java.util.List;
import java.util.Optional;

public interface IEmpresaService {
    public List<Empresa> list();
    public Empresa insert(Empresa empresa);
    public Optional<Empresa> listId(int id);
    public Empresa update(Empresa empresa);
    public void delete(int id);
}
