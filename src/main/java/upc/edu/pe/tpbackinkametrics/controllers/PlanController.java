package upc.edu.pe.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.tpbackinkametrics.dtos.PlanDTO;
import upc.edu.pe.tpbackinkametrics.entities.Plan;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IPlanService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/planes")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class PlanController {
    @Autowired
    private IPlanService pS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<PlanDTO> listar() {
        System.out.println("Entrando a listar Plan");
        return pS.list().stream()
                .map(entity -> {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(entity, PlanDTO.class);
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void insertar(@RequestBody PlanDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Plan entity = modelMapper.map(dto, Plan.class);
        pS.insert(entity);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> actualizar(@RequestBody PlanDTO dto) {
        Optional<Plan> existente = pS.listId(dto.getIdPlan());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plan no encontrado");
        }
        Plan pla = existente.get();
        pla.setNombre(dto.getNombre());
        pla.setLimiteApi(dto.getLimiteApi());
        pla.setPrecioMensual(dto.getPrecioMensual());
        pS.update(pla);
        return ResponseEntity.ok("Plan actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Plan> plan = pS.listId(id);

        if (plan.isPresent()) {
            pS.delete(id);
            return ResponseEntity.ok("Plan eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plan no encontrado");
        }
    }

}
