package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.UsuariosDTO;
import pe.edu.upc.inkametrics.entities.Usuarios;
import pe.edu.upc.inkametrics.servicesinterfaces.IUsuariosService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Usuarios")
public class UsuariosController {
    @Autowired
    private IUsuariosService cS;
    @GetMapping
    public ResponseEntity<List<UsuariosDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<UsuariosDTO> listaUsuarios=cS.list().stream()
                .map(x->m.map(x, UsuariosDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUsuarios);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody UsuariosDTO dto){ // @RequestBody transfiere la peticion para insertar la peticion
        ModelMapper m = new ModelMapper(); // modelmapper transforma el CourseDto a Course
        Usuarios u = m.map(dto, Usuarios.class);

        Usuarios usu = cS.insert(u);
        UsuariosDTO responseDTO = m.map(usu, UsuariosDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) { //responseentity permite gestionar la rpta http
        ModelMapper m = new ModelMapper();
        Optional<Usuarios> usuario = cS.listId(id);

        if (usuario.isPresent()) {
            UsuariosDTO dto = m.map(usuario.get(), UsuariosDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }

    @PutMapping("/actualiza") //es la ruta, solo es la url - nombre para mostrar que representa
    public ResponseEntity<String> actualizar(@RequestBody UsuariosDTO dto) { // @RequestBody transfiere la peticion para actualizar la peticion

        Optional<Usuarios> existente = cS.listId(dto.getIdUsuario());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }

        Usuarios usu = existente.get();

        usu.setEmail(dto.getEmailUsuario());
        usu.setPassword(dto.getPasswordUsuario());

        cS.update(usu);

        return ResponseEntity.ok("Usuario actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) { // @pathvariable indica que tiene una variable en la ruta ("/{id}")
        Optional<Usuarios> usuario = cS.listId(id);

        if (usuario.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }

}
