package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.EmpresaDTO;
import pe.edu.upc.tpbackinkametrics.entities.Empresa;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IEmpresaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empresas")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class EmpresaController {
    @Autowired
    private IEmpresaService eS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<EmpresaDTO> listar() {
        System.out.println("Entrando a listar Empresa");
        return eS.list().stream()
                .map(entity -> {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(entity, EmpresaDTO.class);
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void insertar(@RequestBody EmpresaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Empresa entity = modelMapper.map(dto, Empresa.class);
        eS.insert(entity);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> actualizar(@RequestBody EmpresaDTO dto) {
        Optional<Empresa> existente = eS.listId(dto.getIdEmpresa());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Empresa no encontrado");
        }
        Empresa emp = existente.get();
        emp.setPlan(dto.getPlan());
        emp.setRuc(dto.getRuc());
        emp.setNombreComercial(dto.getNombreComercial());
        eS.update(emp);
        return ResponseEntity.ok("Empresa actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Empresa> empresa = eS.listId(id);

        if (empresa.isPresent()) {
            eS.delete(id);
            return ResponseEntity.ok("Empresa eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Empresa no encontrado");
        }
    }

    @GetMapping("/buscar-por-plan")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> buscarPorPlan(@RequestParam("idPlan") int idPlan) {
        List<Empresa> empresas = eS.buscarPorPlan(idPlan);
        if (empresas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron empresas con el plan indicado.");
        }
        ModelMapper m = new ModelMapper();
        List<EmpresaDTO> listaDto = empresas.stream()
                .map(x -> m.map(x, EmpresaDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaDto);
    }
}