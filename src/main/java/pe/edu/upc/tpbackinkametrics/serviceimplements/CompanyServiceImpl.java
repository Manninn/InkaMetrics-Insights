package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Company;
import pe.edu.upc.tpbackinkametrics.repositories.ICompanyRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.ICompanyService;
import pe.edu.upc.tpbackinkametrics.util.SecurityUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyRepository companyRepository;

    @Override
    public List<Company> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isClient = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("CLIENTE"));

        if (isClient) {
            int companyId = SecurityUtils.getIdEmpresaActual();
            return companyRepository.findById(companyId).map(List::of).orElse(Collections.emptyList());
        }
        return companyRepository.findAll();
    }

    @Override
    public Company insert(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> listId(int id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company update(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void delete(int id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> findByPlan(int planId) {
        return companyRepository.findByPlan(planId);
    }
}
