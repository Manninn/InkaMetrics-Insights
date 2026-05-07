package upc.edu.pe.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.tpbackinkametrics.dtos.MetricaSnapshotDTO;
import upc.edu.pe.tpbackinkametrics.entities.MetricaSnapshot;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IMetricaSnapshotService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metricasSnapshots")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class MetricaSnapshotController {
    @Autowired
    private IMetricaSnapshotService mS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<MetricaSnapshotDTO> listar() {
        System.out.println("Entrando a listar Metricas Snapshots");
        return mS.list().stream()
                .map(entity -> {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(entity, MetricaSnapshotDTO.class);
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void insertar(@RequestBody MetricaSnapshotDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        MetricaSnapshot entity = modelMapper.map(dto, MetricaSnapshot.class);
        mS.insert(entity);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> actualizar(@RequestBody MetricaSnapshotDTO dto) {
        Optional<MetricaSnapshot> existente = mS.listId(dto.getIdMetricaSnapshot());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Metricas Snapshots no encontrado");
        }
        MetricaSnapshot met = existente.get();
        met.setNombre(dto.getNombre());
        met.setCantidad(dto.getCantidad());
        met.setTransmision(dto.getTransmision());
        mS.update(met);
        return ResponseEntity.ok("Metricas Snapshots actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<MetricaSnapshot> metricaSnapshot = mS.listId(id);

        if (metricaSnapshot.isPresent()) {
            mS.delete(id);
            return ResponseEntity.ok("Metricas Snapshots eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Metricas Snapshots no encontrado");
        }
    }
}
