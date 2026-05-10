package upc.edu.pe.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.tpbackinkametrics.dtos.StreamerDTO;
import upc.edu.pe.tpbackinkametrics.dtos.StreamerEspecialDTO;
import upc.edu.pe.tpbackinkametrics.entities.Streamer;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IStreamerService;
import upc.edu.pe.tpbackinkametrics.util.SecurityUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/streamers")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class StreamerController {

    @Autowired
    private IStreamerService sS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<StreamerDTO> listar() {
        System.out.println("Entrando a listar Streamer");
        return sS.list().stream()
                .map(entity -> {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(entity, StreamerDTO.class);
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> registrar(@RequestBody StreamerEspecialDTO dto){
        ModelMapper m = new ModelMapper();
        Streamer s = m.map(dto, Streamer.class);
        s.setFechaRegistroApp(java.time.LocalDate.now());

        Streamer stre = sS.insert(s);
        StreamerEspecialDTO responseDTO = m.map(stre, StreamerEspecialDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> actualizar(@RequestBody StreamerEspecialDTO dto) {
        Optional<Streamer> existente = sS.listId(dto.getIdStreamer());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Streamer no encontrado");
        }
        Streamer str = existente.get();
        str.setNickname(dto.getNickname());
        str.setGenero(dto.getNickname());
        str.setRegion(dto.getRegion());
        sS.update(str);
        return ResponseEntity.ok("Streamer actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Streamer> streamer = sS.listId(id);

        if (streamer.isPresent()) {
            sS.delete(id);
            return ResponseEntity.ok("Streamer eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Streamer no encontrado");
        }
    }

}
