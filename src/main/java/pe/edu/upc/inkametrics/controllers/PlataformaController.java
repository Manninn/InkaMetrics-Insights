package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.PlataformasDTO;
import pe.edu.upc.inkametrics.entities.Plataformas;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlataformasService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Plataforma")
public class PlataformaController {
    @Autowired
    private IPlataformasService cS;
    @GetMapping
    public ResponseEntity<List<PlataformasDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<PlataformasDTO> listaPlataforma=cS.list().stream()
                .map(x->m.map(x, PlataformasDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaPlataforma);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody PlataformasDTO dto){ // @RequestBody transfiere la peticion para insertar la peticion
        ModelMapper m = new ModelMapper(); // modelmapper transforma el CourseDto a Course
        Plataformas p = m.map(dto, Plataformas.class);

        Plataformas pla = cS.insert(p);
        PlataformasDTO responseDTO = m.map(pla, PlataformasDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) { //responseentity permite gestionar la rpta http
        ModelMapper m = new ModelMapper();
        java.util.Optional<Plataformas> plataformas = cS.listId(id);

        if (plataformas.isPresent()) {
            PlataformasDTO dto = m.map(plataformas.get(), PlataformasDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plataforma no encontrada");
        }
    }

    @PutMapping("/actualiza") //es la ruta, solo es la url - nombre para mostrar que representa
    public ResponseEntity<String> actualizar(@RequestBody PlataformasDTO dto) { // @RequestBody transfiere la peticion para actualizar la peticion

        Optional<Plataformas> existente = cS.listId(dto.getId_plataforma());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca no encontrado");
        }

        Plataformas pla = existente.get();

        pla.setNombre(dto.getNombre());
        pla.setUrl_base(dto.getUrl_base());

        cS.update(pla);

        return ResponseEntity.ok("Plataforma actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) { // @pathvariable indica que tiene una variable en la ruta ("/{id}")
        Optional<Plataformas> plataformas = cS.listId(id);

        if (plataformas.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Plataforma eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plataformas no encontrada");
        }
    }

}
