package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.Detecciones_publicitariasDTO;
import pe.edu.upc.inkametrics.dtos.Empresas_clientesDTO;
import pe.edu.upc.inkametrics.entities.Detecciones_publicitarias;
import pe.edu.upc.inkametrics.entities.Empresas_clientes;
import pe.edu.upc.inkametrics.servicesinterfaces.IEmpresas_clientesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Empresas_clientes")
public class Empresas_clientesController {
    @Autowired
    private IEmpresas_clientesService cS;
    @GetMapping
    public ResponseEntity<List<Empresas_clientesDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<Empresas_clientesDTO> listaEmpresas_clientes=cS.list().stream()
                .map(x->m.map(x, Empresas_clientesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaEmpresas_clientes);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody Empresas_clientesDTO dto){ // @RequestBody transfiere la peticion para insertar la peticion
        ModelMapper m = new ModelMapper(); // modelmapper transforma el CourseDto a Course
        Empresas_clientes e = m.map(dto, Empresas_clientes.class);

        Empresas_clientes emp = cS.insert(e);
        Empresas_clientesDTO responseDTO = m.map(emp, Empresas_clientesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) { //responseentity permite gestionar la rpta http
        ModelMapper m = new ModelMapper();
        java.util.Optional<Empresas_clientes> empresascli = cS.listId(id);

        if (empresascli.isPresent()) {
            Empresas_clientesDTO dto = m.map(empresascli.get(), Empresas_clientesDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Empresas clientes no encontrado");
        }
    }

    @PutMapping("/actualiza") //es la ruta, solo es la url - nombre para mostrar que representa
    public ResponseEntity<String> actualizar(@RequestBody Empresas_clientesDTO dto) { // @RequestBody transfiere la peticion para actualizar la peticion

        Optional<Empresas_clientes> existente = cS.listId(dto.getId_empresa());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Empresas clientes no encontrado");
        }

        Empresas_clientes emp = existente.get();

        emp.setNombre_comercial(dto.getNombre_comercial());
        emp.setRuc(dto.getRuc());

        cS.update(emp);

        return ResponseEntity.ok("Empresas clientes actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) { // @pathvariable indica que tiene una variable en la ruta ("/{id}")
        Optional<Empresas_clientes> empresascli = cS.listId(id);

        if (empresascli.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Empresas clientes eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Empresas clientes no encontrado");
        }
    }

}
