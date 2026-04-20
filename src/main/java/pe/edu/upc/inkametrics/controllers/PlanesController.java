package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.PlanesDTO;
import pe.edu.upc.inkametrics.entities.Planes;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlanesService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Planes")
public class PlanesController {
    @Autowired
    private IPlanesService cS;
    @GetMapping
    public ResponseEntity<List<PlanesDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<PlanesDTO> listaPlanes=cS.list().stream()
                .map(x->m.map(x, PlanesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaPlanes);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody PlanesDTO dto){ // @RequestBody transfiere la peticion para insertar la peticion
        ModelMapper m = new ModelMapper(); // modelmapper transforma el CourseDto a Course
        Planes p = m.map(dto, Planes.class);

        Planes pla = cS.insert(p);
        PlanesDTO responseDTO = m.map(pla, PlanesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) { //responseentity permite gestionar la rpta http
        ModelMapper m = new ModelMapper();
        java.util.Optional<Planes> planes = cS.listId(id);

        if (planes.isPresent()) {
            PlanesDTO dto = m.map(planes.get(), PlanesDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plan no encontrada");
        }
    }

    @PutMapping("/actualiza") //es la ruta, solo es la url - nombre para mostrar que representa
    public ResponseEntity<String> actualizar(@RequestBody PlanesDTO dto) { // @RequestBody transfiere la peticion para actualizar la peticion

        Optional<Planes> existente = cS.listId(dto.getId_plan());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plan no encontrado");
        }

        Planes pla = existente.get();

        pla.setNombre(dto.getNombre());
        pla.setPrecio_mensual(dto.getPrecio_mensual());
        pla.setLimite_api(dto.getLimite_api());

        cS.update(pla);

        return ResponseEntity.ok("Plan actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) { // @pathvariable indica que tiene una variable en la ruta ("/{id}")
        Optional<Planes> planes = cS.listId(id);

        if (planes.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Plan eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plan no encontrado");
        }
    }
}
