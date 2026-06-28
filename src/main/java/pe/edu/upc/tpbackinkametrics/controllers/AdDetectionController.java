package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.AdDetectionDTO;
import pe.edu.upc.tpbackinkametrics.dtos.AdDetectionSpecialDTO;
import pe.edu.upc.tpbackinkametrics.entities.AdDetection;
import pe.edu.upc.tpbackinkametrics.entities.Brand;
import pe.edu.upc.tpbackinkametrics.entities.Broadcast;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IAdDetectionService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IBrandService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IBroadcastService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ad-detections")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class AdDetectionController {

    @Autowired
    private IAdDetectionService adDetectionService;
    @Autowired
    private IBroadcastService broadcastService;
    @Autowired
    private IBrandService brandService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<AdDetectionDTO> list() {
        return adDetectionService.list().stream()
                .map(entity -> new ModelMapper().map(entity, AdDetectionDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> insert(@RequestBody AdDetectionSpecialDTO dto) {
        Optional<Broadcast> broadcast = broadcastService.listId(dto.getBroadcastId());
        if (broadcast.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Broadcast not found");
        Optional<Brand> brand = brandService.listId(dto.getBrandId());
        if (brand.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand not found");
        AdDetection d = new AdDetection();
        d.setType(dto.getType());
        d.setAppearanceDurationSec(dto.getAppearanceDurationSec());
        d.setAppearanceTime(java.time.LocalTime.now());
        d.setBroadcast(broadcast.get());
        d.setBrand(brand.get());
        AdDetection saved = adDetectionService.insert(d);
        ModelMapper m = new ModelMapper();
        return ResponseEntity.status(HttpStatus.CREATED).body(m.map(saved, AdDetectionSpecialDTO.class));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> update(@RequestBody AdDetectionSpecialDTO dto) {
        Optional<AdDetection> existing = adDetectionService.listId(dto.getId());
        if (existing.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ad Detection not found");
        Optional<Broadcast> broadcast = broadcastService.listId(dto.getBroadcastId());
        if (broadcast.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Broadcast not found");
        Optional<Brand> brand = brandService.listId(dto.getBrandId());
        if (brand.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand not found");
        AdDetection ad = existing.get();
        ad.setType(dto.getType());
        ad.setAppearanceDurationSec(dto.getAppearanceDurationSec());
        ad.setBroadcast(broadcast.get());
        ad.setBrand(brand.get());
        adDetectionService.update(ad);
        return ResponseEntity.ok("Ad Detection updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<AdDetection> ad = adDetectionService.listId(id);
        if (ad.isPresent()) {
            adDetectionService.delete(id);
            return ResponseEntity.ok("Ad Detection deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ad Detection not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<AdDetection> ad = adDetectionService.listId(id);
        if (ad.isPresent()) {
            AdDetectionDTO dto = new ModelMapper().map(ad.get(), AdDetectionDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ad Detection not found");
        }
    }
}
