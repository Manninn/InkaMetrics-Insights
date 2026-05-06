package upc.edu.pe.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.tpbackinkametrics.dtos.CanalMonitoreadoDTO;
import upc.edu.pe.tpbackinkametrics.entities.CanalMonitoreado;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.ICanalMonitoreadoService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/canalesMonitoreados")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class CanalMonitoreadoController {
    @Autowired
    private ICanalMonitoreadoService cmS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<CanalMonitoreadoDTO> listar() {
        System.out.println("Entrando a listar Canal Monitoreado");
        return cmS.list().stream()
                .map(entity -> {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(entity, CanalMonitoreadoDTO.class);
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void insertar(@RequestBody CanalMonitoreadoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        CanalMonitoreado entity = modelMapper.map(dto, CanalMonitoreado.class);
        cmS.insert(entity);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> actualizar(@RequestBody CanalMonitoreadoDTO dto) {
        Optional<CanalMonitoreado> existente = cmS.listId(dto.getIdCanalMonitoreado());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Canal Monitoreado no encontrado");
        }
        CanalMonitoreado can = existente.get();
        can.setCanal(dto.getCanal());
        can.setEmpresa(dto.getEmpresa());
        cmS.update(can);
        return ResponseEntity.ok("Canal Monitoreado actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<CanalMonitoreado> canalMonitoreado = cmS.listId(id);

        if (canalMonitoreado.isPresent()) {
            cmS.delete(id);
            return ResponseEntity.ok("Canal Monitoreado eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Canal Monitoreado no encontrado");
        }
    }
}
