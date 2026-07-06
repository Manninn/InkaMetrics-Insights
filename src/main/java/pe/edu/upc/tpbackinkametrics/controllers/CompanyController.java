package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.CompanyDTO;
import pe.edu.upc.tpbackinkametrics.entities.Company;
import pe.edu.upc.tpbackinkametrics.entities.Plan;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.ICompanyService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IMonitoredChannelService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IPlanService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class CompanyController {
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IPlanService planService;
    @Autowired
    private IMonitoredChannelService monitoredChannelService;
    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<CompanyDTO> list() {
        return companyService.list().stream()
                .map(entity -> new ModelMapper().map(entity, CompanyDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> insert(@RequestBody CompanyDTO dto) {
        Optional<Plan> plan = planService.listId(dto.getPlanId());
        if (plan.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plan not found");
        Company company = new Company();
        company.setTradeName(dto.getTradeName());
        company.setRuc(dto.getRuc());
        company.setPlan(plan.get());
        companyService.insert(company);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> update(@RequestBody CompanyDTO dto) {
        Optional<Company> existing = companyService.listId(dto.getId());
        if (existing.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        Optional<Plan> plan = planService.listId(dto.getPlanId());
        if (plan.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plan not found");
        Company comp = existing.get();
        comp.setTradeName(dto.getTradeName());
        comp.setRuc(dto.getRuc());
        comp.setPlan(plan.get());
        companyService.update(comp);
        return ResponseEntity.ok("Company updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Company> company = companyService.listId(id);
        if (company.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
        List<String> reasons = new ArrayList<>();
        if (!monitoredChannelService.listByCompany(id).isEmpty()) {
            reasons.add("canales monitoreados");
        }
        boolean hasUsers = userService.list().stream()
                .anyMatch(u -> u.getCompany() != null && u.getCompany().getId() == id);
        if (hasUsers) {
            reasons.add("usuarios");
        }
        if (!reasons.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar la empresa porque tiene " + String.join(" y ", reasons) + " asociados.");
        }
        try {
            companyService.delete(id);
            return ResponseEntity.ok("Company deleted successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar la empresa porque tiene registros asociados.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Company> company = companyService.listId(id);
        if (company.isPresent()) {
            CompanyDTO dto = new ModelMapper().map(company.get(), CompanyDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
    }

    @GetMapping("/find-by-plan")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> findByPlan(@RequestParam("planId") int planId) {
        List<Company> companies = companyService.findByPlan(planId);
        if (companies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No companies found with the given plan.");
        }
        ModelMapper m = new ModelMapper();
        List<CompanyDTO> list = companies.stream()
                .map(x -> m.map(x, CompanyDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
