package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.BroadcastDTO;
import pe.edu.upc.tpbackinkametrics.dtos.BroadcastSpecialDTO;
import pe.edu.upc.tpbackinkametrics.entities.Broadcast;
import pe.edu.upc.tpbackinkametrics.entities.Channel;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IAdDetectionService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IBroadcastService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IChannelService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IMetricSnapshotService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/broadcasts")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class BroadcastController {

    @Autowired
    private IBroadcastService broadcastService;
    @Autowired
    private IChannelService channelService;
    @Autowired
    private IMetricSnapshotService metricSnapshotService;
    @Autowired
    private IAdDetectionService adDetectionService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<BroadcastDTO> list() {
        return broadcastService.list().stream()
                .map(entity -> new ModelMapper().map(entity, BroadcastDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> insert(@RequestBody BroadcastSpecialDTO dto) {
        Optional<Channel> channel = channelService.listId(dto.getChannelId());
        if (channel.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Channel not found");
        Broadcast b = new Broadcast();
        b.setStreamTitle(dto.getStreamTitle());
        b.setStartDate(dto.getStartDate());
        b.setEndDate(dto.getEndDate());
        b.setIsLive(dto.getIsLive());
        b.setChannel(channel.get());
        Broadcast saved = broadcastService.insert(b);
        ModelMapper m = new ModelMapper();
        return ResponseEntity.status(HttpStatus.CREATED).body(m.map(saved, BroadcastSpecialDTO.class));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> update(@RequestBody BroadcastSpecialDTO dto) {
        Optional<Broadcast> existing = broadcastService.listId(dto.getId());
        if (existing.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Broadcast not found");
        Optional<Channel> channel = channelService.listId(dto.getChannelId());
        if (channel.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Channel not found");
        Broadcast br = existing.get();
        br.setStreamTitle(dto.getStreamTitle());
        br.setEndDate(dto.getEndDate());
        br.setIsLive(dto.getIsLive());
        br.setChannel(channel.get());
        broadcastService.update(br);
        return ResponseEntity.ok("Broadcast updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Broadcast> broadcast = broadcastService.listId(id);
        if (broadcast.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Broadcast not found");
        }
        List<String> reasons = new ArrayList<>();
        boolean hasMetrics = metricSnapshotService.list().stream()
                .anyMatch(ms -> ms.getBroadcast() != null && ms.getBroadcast().getId() == id);
        if (hasMetrics) {
            reasons.add("métricas");
        }
        boolean hasAdDetections = adDetectionService.list().stream()
                .anyMatch(ad -> ad.getBroadcast() != null && ad.getBroadcast().getId() == id);
        if (hasAdDetections) {
            reasons.add("detecciones de anuncios");
        }
        if (!reasons.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar la transmisión porque tiene " + String.join(" y ", reasons) + " asociadas.");
        }
        try {
            broadcastService.delete(id);
            return ResponseEntity.ok("Broadcast deleted successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar la transmisión porque tiene registros asociados.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Broadcast> broadcast = broadcastService.listId(id);
        if (broadcast.isPresent()) {
            BroadcastDTO dto = new ModelMapper().map(broadcast.get(), BroadcastDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Broadcast not found");
        }
    }
}
