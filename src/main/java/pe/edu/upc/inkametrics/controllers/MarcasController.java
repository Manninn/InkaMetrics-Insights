package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.MarcasDTO;
import pe.edu.upc.inkametrics.entities.Marcas;
import pe.edu.upc.inkametrics.servicesinterfaces.IMarcasService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Marcas")
public class MarcasController {
    @Autowired
    private IMarcasService cS;
    @GetMapping
    public ResponseEntity<List<MarcasDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<MarcasDTO> listaMarcas=cS.list().stream()
                .map(x->m.map(x, MarcasDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaMarcas);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody MarcasDTO dto){ // @RequestBody transfiere la peticion para insertar la peticion
        ModelMapper m = new ModelMapper(); // modelmapper transforma el CourseDto a Course
        Marcas e = m.map(dto, Marcas.class);

        Marcas mar = cS.insert(e);
        MarcasDTO responseDTO = m.map(mar, MarcasDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) { //responseentity permite gestionar la rpta http
        ModelMapper m = new ModelMapper();
        java.util.Optional<Marcas> marcas = cS.listId(id);

        if (marcas.isPresent()) {
            MarcasDTO dto = m.map(marcas.get(), MarcasDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca no encontrada");
        }
    }

    @PutMapping("/actualiza") //es la ruta, solo es la url - nombre para mostrar que representa
    public ResponseEntity<String> actualizar(@RequestBody MarcasDTO dto) { // @RequestBody transfiere la peticion para actualizar la peticion

        Optional<Marcas> existente = cS.listId(dto.getId_marca());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca no encontrado");
        }

        Marcas mar = existente.get();

        mar.setNombre(dto.getNombre());
        mar.setSector(dto.getSector());

        cS.update(mar);

        return ResponseEntity.ok("Marca actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) { // @pathvariable indica que tiene una variable en la ruta ("/{id}")
        Optional<Marcas> marcas = cS.listId(id);

        if (marcas.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Marca eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca no encontrada");
        }
    }

}
