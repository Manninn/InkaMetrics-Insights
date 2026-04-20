package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.Canales_monitoreadosDTO;
import pe.edu.upc.inkametrics.entities.Canales_monitoreados;
import pe.edu.upc.inkametrics.servicesinterfaces.ICanales_monitoreadosService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Canales_monitoreados")
public class Canales_monitoreadosController {
    @Autowired
    private ICanales_monitoreadosService cS;

    @GetMapping
    public ResponseEntity<List<Canales_monitoreadosDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<Canales_monitoreadosDTO> listaCanales_monitoreados=cS.list().stream()
                .map(x->m.map(x, Canales_monitoreadosDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaCanales_monitoreados);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody Canales_monitoreadosDTO dto){ // @RequestBody transfiere la peticion para insertar la peticion
        ModelMapper m = new ModelMapper(); // modelmapper transforma el CourseDto a Course
        Canales_monitoreados c = m.map(dto, Canales_monitoreados.class);

        Canales_monitoreados cm = cS.insert(c);
        Canales_monitoreadosDTO responseDTO = m.map(cm, Canales_monitoreadosDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) { //responseentity permite gestionar la rpta http
        ModelMapper m = new ModelMapper();
        Optional<Canales_monitoreados> canalmonitoreado = cS.listId(id);

        if (canalmonitoreado.isPresent()) {
            Canales_monitoreadosDTO dto = m.map(canalmonitoreado.get(), Canales_monitoreadosDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Canal no encontrado");
        }
    }

    @PutMapping("/actualiza") //es la ruta, solo es la url - nombre para mostrar que representa
    public ResponseEntity<String> actualizar(@RequestBody Canales_monitoreadosDTO dto) { // @RequestBody transfiere la peticion para actualizar la peticion

        Optional<Canales_monitoreados> existente = cS.listId(dto.getId());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Canal no encontrado");
        }

        Canales_monitoreados cm = existente.get();

        cm.setId(dto.getId());

        cS.update(cm);

        return ResponseEntity.ok("Canal actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) { // @pathvariable indica que tiene una variable en la ruta ("/{id}")
        Optional<Canales_monitoreados> canalesmonitoreados = cS.listId(id);

        if (canalesmonitoreados.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Canal eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Canal no encontrado");
        }
    }

}
