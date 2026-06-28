package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.StreamerDTO;
import pe.edu.upc.tpbackinkametrics.entities.Region;
import pe.edu.upc.tpbackinkametrics.entities.Streamer;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IRegionService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IStreamerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/streamers")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class StreamerController {

    @Autowired
    private IStreamerService streamerService;
    @Autowired
    private IRegionService regionService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<StreamerDTO> list() {
        return streamerService.list().stream()
                .map(entity -> new ModelMapper().map(entity, StreamerDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> insert(@RequestBody StreamerDTO dto) {
        Optional<Region> region = regionService.listId(dto.getRegionId());
        if (region.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Region not found");
        Streamer streamer = new Streamer();
        streamer.setNickname(dto.getNickname());
        streamer.setGender(dto.getGender());
        streamer.setRegistrationDate(java.time.LocalDate.now());
        streamer.setRegion(region.get());
        Streamer saved = streamerService.insert(streamer);
        ModelMapper m = new ModelMapper();
        return ResponseEntity.status(HttpStatus.CREATED).body(m.map(saved, StreamerDTO.class));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> update(@RequestBody StreamerDTO dto) {
        Optional<Streamer> existing = streamerService.listId(dto.getId());
        if (existing.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Streamer not found");
        Optional<Region> region = regionService.listId(dto.getRegionId());
        if (region.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Region not found");
        Streamer str = existing.get();
        str.setNickname(dto.getNickname());
        str.setGender(dto.getGender());
        str.setRegion(region.get());
        streamerService.update(str);
        return ResponseEntity.ok("Streamer updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Streamer> streamer = streamerService.listId(id);
        if (streamer.isPresent()) {
            streamerService.delete(id);
            return ResponseEntity.ok("Streamer deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Streamer not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Streamer> streamer = streamerService.listId(id);
        if (streamer.isPresent()) {
            StreamerDTO dto = new ModelMapper().map(streamer.get(), StreamerDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Streamer not found");
        }
    }
}
