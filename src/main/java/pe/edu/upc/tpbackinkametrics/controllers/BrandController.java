package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.BrandDTO;
import pe.edu.upc.tpbackinkametrics.entities.Brand;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IBrandService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brands")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<BrandDTO> list() {
        return brandService.list().stream()
                .map(entity -> new ModelMapper().map(entity, BrandDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void insert(@RequestBody BrandDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Brand entity = modelMapper.map(dto, Brand.class);
        brandService.insert(entity);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> update(@RequestBody BrandDTO dto) {
        Optional<Brand> existing = brandService.listId(dto.getId());
        if (existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand not found");
        }
        Brand brand = existing.get();
        brand.setName(dto.getName());
        brand.setSector(dto.getSector());
        brandService.update(brand);
        return ResponseEntity.ok("Brand updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Brand> brand = brandService.listId(id);
        if (brand.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand not found");
        }
        try {
            brandService.delete(id);
            return ResponseEntity.ok("Brand deleted successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar la marca porque tiene detecciones de anuncios asociadas.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Brand> brand = brandService.listId(id);
        if (brand.isPresent()) {
            BrandDTO dto = new ModelMapper().map(brand.get(), BrandDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand not found");
        }
    }
}
