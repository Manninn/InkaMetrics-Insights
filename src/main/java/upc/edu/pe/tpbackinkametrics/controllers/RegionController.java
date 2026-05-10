package upc.edu.pe.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.tpbackinkametrics.dtos.RegionDTO;
import upc.edu.pe.tpbackinkametrics.entities.Region;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IRegionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/regiones")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class RegionController {
    @Autowired
    private IRegionService rS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<RegionDTO> listar() {
        System.out.println("Entrando a listar Region");
        return rS.list().stream()
                .map(entity -> {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(entity, RegionDTO.class);
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void insertar(@RequestBody RegionDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Region entity = modelMapper.map(dto, Region.class);
        rS.insert(entity);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> actualizar(@RequestBody RegionDTO dto) {
        Optional<Region> existente = rS.listId(dto.getIdRegion());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Region no encontrado");
        }
        Region reg = existente.get();
        reg.setNombre(dto.getNombre());
        rS.update(reg);
        return ResponseEntity.ok("Region actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Region> region = rS.listId(id);

        if (region.isPresent()) {
            rS.delete(id);
            return ResponseEntity.ok("Region eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Region no encontrado");
        }
    }
}
