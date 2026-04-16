package pe.edu.upc.inkametrics_backend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics_backend.dtos.BrandDTO;
import pe.edu.upc.inkametrics_backend.dtos.DetectionAdvertisingDTO;
import pe.edu.upc.inkametrics_backend.entities.Brand;
import pe.edu.upc.inkametrics_backend.entities.DetectionAdvertising;
import pe.edu.upc.inkametrics_backend.serviceinterfaces.IDetectionAdvertisingService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/detectionadvertising")
public class DetectionAdvertisingController {

    @Autowired
    private IDetectionAdvertisingService daS;

    @GetMapping("/listardeteccion")
    public ResponseEntity<List<DetectionAdvertisingDTO>> listar() {
        ModelMapper m = new ModelMapper();
        List<DetectionAdvertisingDTO> listadeteccion = daS.list().stream()
                .map(x -> m.map(x, DetectionAdvertisingDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listadeteccion);
    }

    @PostMapping("/registrardeteccion")
    public ResponseEntity<?> registrar(@RequestBody BrandDTO dto) {
        ModelMapper m = new ModelMapper();
        DetectionAdvertising da = m.map(dto, DetectionAdvertising.class);
        DetectionAdvertising DetectionGuardada = daS.insert(da);
        DetectionAdvertisingDTO responseDTO = m.map(DetectionGuardada, DetectionAdvertisingDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    @PutMapping("/actualizardeteccion")
    public ResponseEntity<String> actualizar(@RequestBody DetectionAdvertisingDTO dto) {
        Optional<DetectionAdvertising> existente = daS.listId(dto.getIdDetectionAdvertising());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Deteccion no encontrada");
        }
        DetectionAdvertising da = existente.get();
        da.setIdDetectionAdvertising(dto.getIdDetectionAdvertising());
        da.setTypeTransmission(dto.getTypeTransmission());
        da.setSecondAppearanceTransmission(dto.getSecondAppearanceTransmission());
        daS.insert(da);
        return ResponseEntity.ok("Deteccion actualizada correctamente");
    }

    @DeleteMapping("/borrardeteccion/{idDetection}")
    public ResponseEntity<String> eliminar(@PathVariable int idDetectionAdvertising) {
        Optional<DetectionAdvertising> deteccion = daS.listId(idDetectionAdvertising);

        if (deteccion.isPresent()) {
            daS.delete(idDetectionAdvertising);
            return ResponseEntity.ok("Deteccion eliminada correctamenta");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Deteccion no encontrada");
        }
    }
}









