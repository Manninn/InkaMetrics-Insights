package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.RegionesDTO;
import pe.edu.upc.inkametrics.entities.Regiones;
import pe.edu.upc.inkametrics.servicesinterfaces.IRegionesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Regiones")
public class RegionesController {
    @Autowired
    private IRegionesService cS;

    @PostMapping
    public void registrar(@RequestBody RegionesDTO dto) {
        ModelMapper m = new ModelMapper();
        Regiones d = m.map(dto, Regiones.class);
        cS.insert(d);
    }

    @GetMapping
    public ResponseEntity<List<RegionesDTO>> listar() {
        ModelMapper m = new ModelMapper();
        List<RegionesDTO> listaRegiones = cS.list().stream()
                .map(x -> m.map(x, RegionesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaRegiones);
    }

    @PutMapping
    public void modificar(@RequestBody RegionesDTO dto) {
        ModelMapper m = new ModelMapper();
        Regiones d = m.map(dto, Regiones.class);
        cS.insert(d);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        cS.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionesDTO> listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        RegionesDTO dto = m.map(cS.listId(id), RegionesDTO.class);
        return ResponseEntity.ok(dto);
    }
}