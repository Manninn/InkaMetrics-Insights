package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.RegionesDTO;
import pe.edu.upc.inkametrics.entities.Regiones;
import pe.edu.upc.inkametrics.servicesinterfaces.IRegionesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Regiones")
public class RegionesController {
    @Autowired
    private IRegionesService cS;
    @GetMapping
    public ResponseEntity<List<RegionesDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<RegionesDTO> listaRegiones=cS.list().stream()
                .map(x->m.map(x, RegionesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaRegiones);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody RegionesDTO dto){ // @RequestBody transfiere la peticion para insertar la peticion
        ModelMapper m = new ModelMapper(); // modelmapper transforma el CourseDto a Course
        Regiones r = m.map(dto, Regiones.class);

        Regiones reg = cS.insert(r);
        RegionesDTO responseDTO = m.map(reg, RegionesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) { //responseentity permite gestionar la rpta http
        ModelMapper m = new ModelMapper();
        java.util.Optional<Regiones> regiones = cS.listId(id);

        if (regiones.isPresent()) {
            RegionesDTO dto = m.map(regiones.get(), RegionesDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Regiones no encontradas");
        }
    }

    @PutMapping("/actualiza") //es la ruta, solo es la url - nombre para mostrar que representa
    public ResponseEntity<String> actualizar(@RequestBody RegionesDTO dto) { // @RequestBody transfiere la peticion para actualizar la peticion

        Optional<Regiones> existente = cS.listId(dto.getId_region());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Regiones no encontradas");
        }

        Regiones reg = existente.get();

        reg.setNombre(dto.getNombre());
        reg.setNombre(dto.getNombre());

        cS.update(reg);

        return ResponseEntity.ok("Region actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) { // @pathvariable indica que tiene una variable en la ruta ("/{id}")
        Optional<Regiones> regiones = cS.listId(id);

        if (regiones.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Region eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Region no encontrada");
        }
    }

}
