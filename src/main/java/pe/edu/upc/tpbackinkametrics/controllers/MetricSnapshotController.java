package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.MetricSnapshotDTO;
import pe.edu.upc.tpbackinkametrics.dtos.MetricsByBroadcastDTO;
import pe.edu.upc.tpbackinkametrics.dtos.MetricsByRegionDTO;
import pe.edu.upc.tpbackinkametrics.entities.Broadcast;
import pe.edu.upc.tpbackinkametrics.entities.MetricSnapshot;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IMetricSnapshotService;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IBroadcastService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metrics")
public class MetricSnapshotController {
    @Autowired
    private IMetricSnapshotService metricSnapshotService;
    @Autowired
    private IBroadcastService broadcastService;

    @GetMapping("/list")
    public List<MetricSnapshotDTO> list() {
        return metricSnapshotService.list().stream()
                .map(entity -> new ModelMapper().map(entity, MetricSnapshotDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> insert(@RequestBody MetricSnapshotDTO dto) {
        Optional<Broadcast> broadcast = broadcastService.listId(dto.getBroadcastId());
        if (broadcast.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Broadcast not found");
        MetricSnapshot entity = new MetricSnapshot();
        entity.setName(dto.getName());
        entity.setAmount(dto.getAmount());
        entity.setBroadcast(broadcast.get());
        metricSnapshotService.insert(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> update(@RequestBody MetricSnapshotDTO dto) {
        Optional<MetricSnapshot> existing = metricSnapshotService.listId(dto.getId());
        if (existing.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Metric Snapshot not found");
        Optional<Broadcast> broadcast = broadcastService.listId(dto.getBroadcastId());
        if (broadcast.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Broadcast not found");
        MetricSnapshot ms = existing.get();
        ms.setName(dto.getName());
        ms.setAmount(dto.getAmount());
        ms.setBroadcast(broadcast.get());
        metricSnapshotService.update(ms);
        return ResponseEntity.ok("Metric Snapshot updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<MetricSnapshot> ms = metricSnapshotService.listId(id);
        if (ms.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Metric Snapshot not found");
        }
        try {
            metricSnapshotService.delete(id);
            return ResponseEntity.ok("Metric Snapshot deleted successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar esta métrica porque tiene registros asociados.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<MetricSnapshot> ms = metricSnapshotService.listId(id);
        if (ms.isPresent()) {
            MetricSnapshotDTO dto = new ModelMapper().map(ms.get(), MetricSnapshotDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Metric not found");
        }
    }

    @GetMapping("/metric-names")
    public ResponseEntity<List<String>> getMetricNames() {
        List<String> names = metricSnapshotService.findDistinctNames();
        if (names.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(names);
    }

    @GetMapping("/reporte-top-transmisiones")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public ResponseEntity<List<MetricsByBroadcastDTO>> getTopBroadcastsReport(@RequestParam String metricName) {
        List<Object[]> rows = metricSnapshotService.reportMetricsNative(metricName);
        List<MetricsByBroadcastDTO> response = new ArrayList<>();
        for (Object[] row : rows) {
            MetricsByBroadcastDTO dto = new MetricsByBroadcastDTO();
            dto.setStreamTitle(String.valueOf(row[0]));
            dto.setMetricName(String.valueOf(row[1]));
            dto.setTotalAmount(Integer.parseInt(String.valueOf(row[2])));
            response.add(dto);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reporte-metricas-por-region")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public ResponseEntity<List<MetricsByRegionDTO>> getMetricsByRegionReport() {
        List<Object[]> rows = metricSnapshotService.findMetricsPerformanceByRegionNative();
        if (rows.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<MetricsByRegionDTO> response = new ArrayList<>();
        for (Object[] row : rows) {
            MetricsByRegionDTO dto = new MetricsByRegionDTO();
            dto.setRegionName(String.valueOf(row[0]));
            dto.setMetricName(String.valueOf(row[1]));
            dto.setAverageAmount(Double.parseDouble(String.valueOf(row[2])));
            response.add(dto);
        }
        return ResponseEntity.ok(response);
    }
}
