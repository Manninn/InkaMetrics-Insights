package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.CanalesDTO;
import pe.edu.upc.inkametrics.entities.Canales;
import pe.edu.upc.inkametrics.entities.Usuarios;
import pe.edu.upc.inkametrics.servicesinterfaces.ICanalesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Canales") // importante buscar en postman, con las mismas mayusculas
public class CanalesController {
    @Autowired
    private ICanalesService cS;

    @GetMapping
    public ResponseEntity<List<CanalesDTO>>listar() {
        ModelMapper m =new ModelMapper();
        List<CanalesDTO> listaCanales=cS.list().stream()
                .map(x->m.map(x, CanalesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaCanales);
    }
    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody CanalesDTO dto){ // @RequestBody transfiere la peticion para insertar la peticion
        ModelMapper m = new ModelMapper(); // modelmapper transforma el CourseDto a Course
        Canales c = m.map(dto, Canales.class);

        Canales can = cS.insert(c);
        CanalesDTO responseDTO = m.map(can, CanalesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) { //responseentity permite gestionar la rpta http
        ModelMapper m = new ModelMapper();
        Optional<Canales> canal = cS.listId(id);

        if (canal.isPresent()) {
            CanalesDTO dto = m.map(canal.get(), CanalesDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Canal no encontrado");
        }
    }

    @PutMapping("/actualiza") //es la ruta, solo es la url - nombre para mostrar que representa
    public ResponseEntity<String> actualizar(@RequestBody CanalesDTO dto) { // @RequestBody transfiere la peticion para actualizar la peticion

        Optional<Canales> existente = cS.listId(dto.getId_canal());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Canal no encontrado");
        }

        Canales can = existente.get();

        can.setUrl_canal(dto.getUrl_canal());
        can.setSeguidores_actuales(dto.getSeguidores_actuales());

        cS.update(can);

        return ResponseEntity.ok("Canal actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) { // @pathvariable indica que tiene una variable en la ruta ("/{id}")
        Optional<Canales> canal = cS.listId(id);

        if (canal.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Canal eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Canal no encontrado");
        }
    }
}
