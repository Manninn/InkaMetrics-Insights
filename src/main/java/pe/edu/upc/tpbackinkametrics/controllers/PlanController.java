package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.PlanDTO;
import pe.edu.upc.tpbackinkametrics.entities.Plan;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IPlanService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plans")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class PlanController {
    @Autowired
    private IPlanService planService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<PlanDTO> list() {
        return planService.list().stream()
                .map(entity -> new ModelMapper().map(entity, PlanDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void insert(@RequestBody PlanDTO dto) {
        Plan entity = new ModelMapper().map(dto, Plan.class);
        planService.insert(entity);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> update(@RequestBody PlanDTO dto) {
        Optional<Plan> existing = planService.listId(dto.getId());
        if (existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plan not found");
        }
        Plan plan = existing.get();
        plan.setName(dto.getName());
        plan.setApiLimit(dto.getApiLimit());
        plan.setMonthlyPrice(dto.getMonthlyPrice());
        planService.update(plan);
        return ResponseEntity.ok("Plan updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Plan> plan = planService.listId(id);
        if (plan.isPresent()) {
            planService.delete(id);
            return ResponseEntity.ok("Plan deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plan not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Plan> plan = planService.listId(id);
        if (plan.isPresent()) {
            PlanDTO dto = new ModelMapper().map(plan.get(), PlanDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plan not found");
        }
    }
}
