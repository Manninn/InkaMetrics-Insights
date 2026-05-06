package upc.edu.pe.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.tpbackinkametrics.dtos.TransmisionDTO;
import upc.edu.pe.tpbackinkametrics.dtos.TransmisionEspecialDTO;
import upc.edu.pe.tpbackinkametrics.entities.Transmision;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.ITransmisionService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transmisiones")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class TransmisionController {

    @Autowired
    private ITransmisionService tS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<TransmisionDTO> listar() {
        System.out.println("Entrando a listar Transmision");
        return tS.list().stream()
                .map(entity -> {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(entity, TransmisionDTO.class);
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> registrar(@RequestBody TransmisionEspecialDTO dto){
        ModelMapper m = new ModelMapper();
        Transmision t = m.map(dto, Transmision.class);
        t.setFechaInicio(java.time.LocalDate.now());

        Transmision stre = tS.insert(t);
        TransmisionEspecialDTO responseDTO = m.map(stre, TransmisionEspecialDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> actualizar(@RequestBody TransmisionEspecialDTO dto) {
        Optional<Transmision> existente = tS.listId(dto.getIdTransmision());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transmision no encontrado");
        }
        Transmision tr = existente.get();
        tr.setTituloStream(dto.getTituloStream());
        tr.setFechaFin(dto.getFechaFin());
        tr.setEnVivo(dto.isEnVivo());
        tr.setCanal(dto.getCanal());
        tS.update(tr);
        return ResponseEntity.ok("Transmision actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Transmision> transmision = tS.listId(id);

        if (transmision.isPresent()) {
            tS.delete(id);
            return ResponseEntity.ok("Transmision eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transmision no encontrado");
        }
    }
}
