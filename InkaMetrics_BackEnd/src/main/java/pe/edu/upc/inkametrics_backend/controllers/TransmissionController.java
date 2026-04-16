package pe.edu.upc.inkametrics_backend.controllers;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics_backend.dtos.BrandDTO;
import pe.edu.upc.inkametrics_backend.dtos.TransmissionDTO;
import pe.edu.upc.inkametrics_backend.entities.Brand;
import pe.edu.upc.inkametrics_backend.entities.Transmission;
import pe.edu.upc.inkametrics_backend.serviceinterfaces.ITransmissionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transmission")
public class TransmissionController {
    @Autowired
    private ITransmissionService tmiS;

    @GetMapping("/listatrasmission")
    public ResponseEntity<List<TransmissionDTO>> listar() {
        ModelMapper m = new ModelMapper();
        List<TransmissionDTO> listatransmission = tmiS.list().stream()
                .map(x -> m.map(x, TransmissionDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listatransmission);
    }

    @PostMapping("/regsitratrasmission")
    public ResponseEntity<?> registrar(@RequestBody TransmissionDTO dto) {
        ModelMapper m = new ModelMapper();
        Transmission t = m.map(dto, Transmission.class);
        Transmission transmissionGuardada = tmiS.insert(t);
        TransmissionDTO responseDTO = m.map(transmissionGuardada, TransmissionDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualizartransmission")
    public ResponseEntity<String> actualizar(@RequestBody TransmissionDTO dto) {
        Optional<Transmission> existente = tmiS.listId(dto.getIdTransmission());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transmission  no encontrada");
        }
        Transmission tra = existente.get();
        tra.setIdTransmission(dto.getIdTransmission());
        tra.setTitleStream(dto.getTitleStream());
        tra.setDateStart(dto.getDateStart());
        tra.setDateEnd(dto.getDateEnd());
        tmiS.insert(tra);
        return ResponseEntity.ok("Transmission actualizada correctamente");

    }

    @DeleteMapping("/borrartransmission/{idBrand}")
    public ResponseEntity<String> eliminar(@PathVariable int idTransmission) {
        Optional<Transmission> marca = tmiS.listId(idTransmission);

        if (marca.isPresent()) {
            tmiS.delete(idTransmission);
            return ResponseEntity.ok("Transmission eliminada correctamenta");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transmission no encontrada");
        }


    }
}
