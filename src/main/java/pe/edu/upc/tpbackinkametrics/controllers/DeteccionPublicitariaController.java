package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.*;
import pe.edu.upc.tpbackinkametrics.entities.DeteccionPublicitaria;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IDeteccionPublicitariaService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deteccionesPublicitarias")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class DeteccionPublicitariaController {

    @Autowired
    private IDeteccionPublicitariaService dS;

    @GetMapping
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
    @GetMapping("/conteo-por-marca")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> getConteoPorMarca() {
        List<String[]> listaRaw = dS.countDeteccionMarca();

        if (listaRaw.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron datos para el reporte de marcas.");
        }

        List<DeteccionPublicitariaCantidadDTO> listaDto = new ArrayList<>();
        for (String[] data : listaRaw) {
            DeteccionPublicitariaCantidadDTO dto = new DeteccionPublicitariaCantidadDTO();
            dto.setNombreMarca(data[0]); // El nombre de la marca
            dto.setCantidadDetecciones(Long.parseLong(String.valueOf(data[1]))); // El COUNT
            listaDto.add(dto);
        }
        return ResponseEntity.ok(listaDto);
    }

    @GetMapping("/suma-por-tipo")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> getSumaPorTipo() {
        List<String[]> listaRaw = dS.sumDurationByTipoPublicidad();

        if (listaRaw.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron datos para el reporte de tiempos por tipo.");
        }

        List<SumaPublicidadDTO> listaDto = new ArrayList<>();
        for (String[] data : listaRaw) {
            SumaPublicidadDTO dto = new SumaPublicidadDTO();
            dto.setTipo(data[0]);
            dto.setTotalDuracionSeg(Long.parseLong(String.valueOf(data[1]))); // El SUM de duración
            listaDto.add(dto);
        }
        return ResponseEntity.ok(listaDto);
    }

    @GetMapping("/eficiencia-streamers")
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<EficienciaStreamerDTO> verEficiencia() {
        return dS.reporteEficiencia();
    }
}
