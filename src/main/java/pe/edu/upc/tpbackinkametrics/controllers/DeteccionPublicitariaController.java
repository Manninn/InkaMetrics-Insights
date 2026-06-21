package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.DeteccionPublicitariaDTO;
import pe.edu.upc.tpbackinkametrics.dtos.DeteccionPublicitariaEspecialDTO;
import pe.edu.upc.tpbackinkametrics.entities.DeteccionPublicitaria;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IDeteccionPublicitariaService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deteccionesPublicitarias")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class DeteccionPublicitariaController {

    @Autowired
    private IDeteccionPublicitariaService dS;

    @GetMapping("/lista")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<DeteccionPublicitariaDTO> listar() {
        System.out.println("Entrando a listar Deteccion Publicitaria");
        return dS.list().stream()
                .map(entity -> {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(entity, DeteccionPublicitariaDTO.class);
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> registrar(@RequestBody DeteccionPublicitariaEspecialDTO dto){
        ModelMapper m = new ModelMapper();
        DeteccionPublicitaria d = m.map(dto, DeteccionPublicitaria.class);
        d.setMinutoAparicion(java.time.LocalTime.now());

        DeteccionPublicitaria det = dS.insert(d);
        DeteccionPublicitariaEspecialDTO responseDTO = m.map(det, DeteccionPublicitariaEspecialDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> actualizar(@RequestBody DeteccionPublicitariaEspecialDTO dto) {
        Optional<DeteccionPublicitaria> existente = dS.listId(dto.getIdDeteccionPublicitaria());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Deteccion Publicitaria no encontrado");
        }
        DeteccionPublicitaria detec = existente.get();
        detec.setTipo(dto.getTipo());
        detec.setTiempoAparicionSeg(dto.getTiempoAparicionSeg());
        detec.setTransmision(dto.getTransmision());
        detec.setMarca(dto.getMarca());
        dS.update(detec);
        return ResponseEntity.ok("Deteccion Publicitaria actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<DeteccionPublicitaria> deteccionPublicitaria = dS.listId(id);

        if (deteccionPublicitaria.isPresent()) {
            dS.delete(id);
            return ResponseEntity.ok("Deteccion Publicitaria eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Deteccion Publicitaria no encontrado");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<DeteccionPublicitaria> deteccionPublicitaria = dS.listId(id);

        if (deteccionPublicitaria.isPresent()) {
            DeteccionPublicitariaDTO dto = m.map(deteccionPublicitaria.get(), DeteccionPublicitariaDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Detección Publicitaria no encontrada");
        }
    }
}
