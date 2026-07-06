package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.PlatformDTO;
import pe.edu.upc.tpbackinkametrics.entities.Platform;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IPlatformService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/platforms")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class PlatformController {
    @Autowired
    private IPlatformService platformService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<PlatformDTO> list() {
        return platformService.list().stream()
                .map(entity -> new ModelMapper().map(entity, PlatformDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void insert(@RequestBody PlatformDTO dto) {
        Platform entity = new ModelMapper().map(dto, Platform.class);
        platformService.insert(entity);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> update(@RequestBody PlatformDTO dto) {
        Optional<Platform> existing = platformService.listId(dto.getId());
        if (existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Platform not found");
        }
        Platform plat = existing.get();
        plat.setName(dto.getName());
        plat.setBaseUrl(dto.getBaseUrl());
        platformService.update(plat);
        return ResponseEntity.ok("Platform updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Platform> platform = platformService.listId(id);
        if (platform.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Platform not found");
        }
        try {
            platformService.delete(id);
            return ResponseEntity.ok("Platform deleted successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar la plataforma porque tiene canales asociados.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Platform> platform = platformService.listId(id);
        if (platform.isPresent()) {
            PlatformDTO dto = new ModelMapper().map(platform.get(), PlatformDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Platform not found");
        }
    }
}
