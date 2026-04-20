package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.TransmisionesDTO;
import pe.edu.upc.inkametrics.entities.Transmisiones;
import pe.edu.upc.inkametrics.servicesinterfaces.ITransmisionesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Transmisiones")
public class TransmisionesController {
    @Autowired
    private ITransmisionesService cS;
    @GetMapping
    public ResponseEntity<List<TransmisionesDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<TransmisionesDTO> listaTransmisiones=cS.list().stream()
                .map(x->m.map(x, TransmisionesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaTransmisiones);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody TransmisionesDTO dto){ // @RequestBody transfiere la peticion para insertar la peticion
        ModelMapper m = new ModelMapper(); // modelmapper transforma el CourseDto a Course
        Transmisiones t = m.map(dto, Transmisiones.class);

        Transmisiones tra = cS.insert(t);
        TransmisionesDTO responseDTO = m.map(tra, TransmisionesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) { //responseentity permite gestionar la rpta http
        ModelMapper m = new ModelMapper();
        java.util.Optional<Transmisiones> transmisiones = cS.listId(id);

        if (transmisiones.isPresent()) {
            TransmisionesDTO dto = m.map(transmisiones.get(), TransmisionesDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transmisión no encontrada");
        }
    }

    @PutMapping("/actualiza") //es la ruta, solo es la url - nombre para mostrar que representa
    public ResponseEntity<String> actualizar(@RequestBody TransmisionesDTO dto) { // @RequestBody transfiere la peticion para actualizar la peticion

        Optional<Transmisiones> existente = cS.listId(dto.getId_transmision());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca no encontrado");
        }

        Transmisiones tra = existente.get();

        tra.setTitulo_stream(dto.getTitulo_stream());
        tra.setFecha_inicio(dto.getFecha_inicio());

        cS.update(tra);

        return ResponseEntity.ok("Transmisión actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) { // @pathvariable indica que tiene una variable en la ruta ("/{id}")
        Optional<Transmisiones> transmisiones = cS.listId(id);

        if (transmisiones.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Transmisión eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transmisión no encontrada");
        }
    }

}
