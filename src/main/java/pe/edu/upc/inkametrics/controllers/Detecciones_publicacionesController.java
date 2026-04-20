package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.Detecciones_publicitariasDTO;
import pe.edu.upc.inkametrics.entities.Detecciones_publicitarias;
import pe.edu.upc.inkametrics.servicesinterfaces.IDetecciones_publicitariasService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Detecciones_publicaciones")
public class Detecciones_publicacionesController {
    @Autowired
    private IDetecciones_publicitariasService cS;
    @GetMapping
    public ResponseEntity<List<Detecciones_publicitariasDTO>>listar() {
        ModelMapper m =new ModelMapper();
        List<Detecciones_publicitariasDTO> listaDetecciones_publicitarias=cS.list().stream()
                .map(x->m.map(x, Detecciones_publicitariasDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDetecciones_publicitarias);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody Detecciones_publicitariasDTO dto){ // @RequestBody transfiere la peticion para insertar la peticion
        ModelMapper m = new ModelMapper(); // modelmapper transforma el CourseDto a Course
        Detecciones_publicitarias d = m.map(dto, Detecciones_publicitarias.class);

        Detecciones_publicitarias det = cS.insert(d);
        Detecciones_publicitariasDTO responseDTO = m.map(det, Detecciones_publicitariasDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) { //responseentity permite gestionar la rpta http
        ModelMapper m = new ModelMapper();
        java.util.Optional<Detecciones_publicitarias> detecciones = cS.listId(id);

        if (detecciones.isPresent()) {
            Detecciones_publicitariasDTO dto = m.map(detecciones.get(), Detecciones_publicitariasDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Detecciones publicitarias no encontrado");
        }
    }

    @PutMapping("/actualiza") //es la ruta, solo es la url - nombre para mostrar que representa
    public ResponseEntity<String> actualizar(@RequestBody Detecciones_publicitariasDTO dto) { // @RequestBody transfiere la peticion para actualizar la peticion

        Optional<Detecciones_publicitarias> existente = cS.listId(dto.getId());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Detecciones publicitarias no encontrado");
        }

        Detecciones_publicitarias det = existente.get();

        det.setTipo(dto.getTipo());
        det.setDuracion_seg(dto.getDuracion_seg());
        det.setSegundo_aparicion(dto.getSegundo_aparicion());

        cS.update(det);

        return ResponseEntity.ok("Detecciones publicitarias actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) { // @pathvariable indica que tiene una variable en la ruta ("/{id}")
        Optional<Detecciones_publicitarias> deteccionespubli = cS.listId(id);

        if (deteccionespubli.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Detecciones publicitarias eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Detecciones publicitarias no encontrado");
        }
    }
}
