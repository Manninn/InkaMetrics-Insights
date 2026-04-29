package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.MarcasDTO;
import pe.edu.upc.inkametrics.entities.Marcas;
import pe.edu.upc.inkametrics.servicesinterfaces.IMarcasService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Marcas")
public class MarcasController {
    @Autowired
    private IMarcasService cS;

    @PostMapping
    public void registrar(@RequestBody MarcasDTO dto) {
        ModelMapper m = new ModelMapper();
        Marcas d = m.map(dto, Marcas.class);
        cS.insert(d);
    }

    @GetMapping
    public ResponseEntity<List<MarcasDTO>> listar() {
        ModelMapper m = new ModelMapper();
        List<MarcasDTO> listaMarcas = cS.list().stream()
                .map(x -> m.map(x, MarcasDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaMarcas);
    }

    @PutMapping
    public void modificar(@RequestBody MarcasDTO dto) {
        ModelMapper m = new ModelMapper();
        Marcas d = m.map(dto, Marcas.class);
        cS.insert(d);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        cS.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcasDTO> listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        MarcasDTO dto = m.map(cS.listId(id), MarcasDTO.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MarcasDTO>> buscarPorNombre(@RequestParam("nombre") String nombre) {
        ModelMapper m = new ModelMapper();
        List<MarcasDTO> lista = cS.buscarPorNombre(nombre).stream()
                .map(x -> m.map(x, MarcasDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
}