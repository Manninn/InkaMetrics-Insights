package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.CanalDTO;
import pe.edu.upc.tpbackinkametrics.dtos.CanalMonitoreadoDTO;
import pe.edu.upc.tpbackinkametrics.entities.Canal;
import pe.edu.upc.tpbackinkametrics.entities.CanalMonitoreado;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.ICanalService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/canales")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class CanalController {
    @Autowired
    private ICanalService cS;

    @GetMapping("/lista")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<CanalDTO> listar() {
        System.out.println("Entrando a listar Canal");
        return cS.list().stream()
                .map(entity -> {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(entity, CanalDTO.class);
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void insertar(@RequestBody CanalDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Canal entity = modelMapper.map(dto, Canal.class);
        cS.insert(entity);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> actualizar(@RequestBody CanalDTO dto) {
        Optional<Canal> existente = cS.listId(dto.getIdCanal());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Canal no encontrado");
        }
        Canal ca = existente.get();
        ca.setUrlCanal(dto.getUrlCanal());
        ca.setSeguidoresActuales(dto.getSeguidoresActuales());
        ca.setPlataforma(dto.getPlataforma());
        ca.setStreamer(dto.getStreamer());
        cS.update(ca);
        return ResponseEntity.ok("Canal actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Canal> canal = cS.listId(id);

        if (canal.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Canal eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Canal no encontrado");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Canal> canal = cS.listId(id);

        if (canal.isPresent()) {
            CanalDTO dto = m.map(canal.get(), CanalDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Canal no encontrado");
        }
    }
}
