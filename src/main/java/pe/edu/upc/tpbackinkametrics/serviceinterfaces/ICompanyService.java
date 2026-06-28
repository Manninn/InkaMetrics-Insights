package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.Company;

import java.util.List;
import java.util.Optional;

public interface ICompanyService {
    public List<Company> list();
    public Company insert(Company company);
    public Optional<Company> listId(int id);
    public Company update(Company company);
    public void delete(int id);
    public List<Company> findByPlan(int planId);
}
