package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.RegionDTO;
import pe.edu.upc.tpbackinkametrics.entities.Region;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IRegionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/regions")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class RegionController {
    @Autowired
    private IRegionService regionService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<RegionDTO> list() {
        return regionService.list().stream()
                .map(entity -> new ModelMapper().map(entity, RegionDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void insert(@RequestBody RegionDTO dto) {
        Region entity = new ModelMapper().map(dto, Region.class);
        regionService.insert(entity);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> update(@RequestBody RegionDTO dto) {
        Optional<Region> existing = regionService.listId(dto.getId());
        if (existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Region not found");
        }
        Region reg = existing.get();
        reg.setName(dto.getName());
        regionService.update(reg);
        return ResponseEntity.ok("Region updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Region> region = regionService.listId(id);
        if (region.isPresent()) {
            regionService.delete(id);
            return ResponseEntity.ok("Region deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Region not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Region> region = regionService.listId(id);
        if (region.isPresent()) {
            RegionDTO dto = new ModelMapper().map(region.get(), RegionDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Region not found");
        }
    }

    @GetMapping("/find-by-name")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public ResponseEntity<?> findByName(@RequestParam("name") String name) {
        List<Region> regions = regionService.findByName(name);
        if (regions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No regions found matching: " + name);
        }
        ModelMapper m = new ModelMapper();
        List<RegionDTO> list = regions.stream()
                .map(x -> m.map(x, RegionDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
