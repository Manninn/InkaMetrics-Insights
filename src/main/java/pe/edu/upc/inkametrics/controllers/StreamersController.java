package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.StreamersDTO;
import pe.edu.upc.inkametrics.entities.Streamers;
import pe.edu.upc.inkametrics.servicesinterfaces.IStreamersService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Streamers")
public class StreamersController {
    @Autowired
    private IStreamersService cS;
    @GetMapping
    public ResponseEntity<List<StreamersDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<StreamersDTO> listaStreamers=cS.list().stream()
                .map(x->m.map(x, StreamersDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaStreamers);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody StreamersDTO dto){ // @RequestBody transfiere la peticion para insertar la peticion
        ModelMapper m = new ModelMapper(); // modelmapper transforma el CourseDto a Course
        Streamers s = m.map(dto, Streamers.class);

        Streamers str = cS.insert(s);
        StreamersDTO responseDTO = m.map(str, StreamersDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) { //responseentity permite gestionar la rpta http
        ModelMapper m = new ModelMapper();
        java.util.Optional<Streamers> marcas = cS.listId(id);

        if (marcas.isPresent()) {
            StreamersDTO dto = m.map(marcas.get(), StreamersDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca no encontrada");
        }
    }

    @PutMapping("/actualiza") //es la ruta, solo es la url - nombre para mostrar que representa
    public ResponseEntity<String> actualizar(@RequestBody StreamersDTO dto) { // @RequestBody transfiere la peticion para actualizar la peticion

        Optional<Streamers> existente = cS.listId(dto.getId_streamer());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca no encontrado");
        }

        Streamers str = existente.get();

        str.setNickname(dto.getNickname());
        str.setGenero(dto.getGenero());
        str.setFecha_registro_app(dto.getFecha_registro_app());
        cS.update(str);

        return ResponseEntity.ok("Marca actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) { // @pathvariable indica que tiene una variable en la ruta ("/{id}")
        Optional<Streamers> streamers = cS.listId(id);

        if (streamers.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Streamer eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Streamer no encontrada");
        }
    }

}
