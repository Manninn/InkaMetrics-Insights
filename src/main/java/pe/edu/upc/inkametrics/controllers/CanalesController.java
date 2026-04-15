package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.CanalesDTO;
import pe.edu.upc.inkametrics.entities.Canales;
import pe.edu.upc.inkametrics.servicesinterfaces.ICanalesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Canales") // importante buscar en postman, con las mismas mayusculas
public class CanalesController {
    @Autowired
    private ICanalesService cS;
    @GetMapping
    public ResponseEntity<List<CanalesDTO>>listar() {
        ModelMapper m =new ModelMapper();
        List<CanalesDTO> listaCanales=cS.list().stream()
                .map(x->m.map(x, CanalesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaCanales);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?>registrarCanale(@RequestBody CanalesDTO canalesDTO){
        ModelMapper m =new ModelMapper();
        Canales cn= m.map(canalesDTO, Canales.class);

        Canales c = cS.insert(cn);
        CanalesDTO cnDTO = m.map(c, CanalesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(cnDTO);
    }

    @PutMapping("/actualiza")
    public ResponseEntity<?> actualizarCanale(@RequestBody CanalesDTO canalesDTO){
        ModelMapper m =new ModelMapper();
        Optional<Canales> canalExiste = cS.findById(canalesDTO.getId_canal());
        if (canalExiste.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El plataforma no existe");
        }
        Canales cn = canalExiste.get();
        cn.setUrl_canal(canalesDTO.getUrl_canal());
        cn.setSeguidores_actuales(canalesDTO.getSeguidores_actuales());

        Canales actualizados = cS.update(cn);
        CanalesDTO dto = m.map(actualizados, CanalesDTO.class);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCanale(@PathVariable int id){
        Optional<Canales> canal = cS.findById(id);

        if (canal.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Eliminado con exito.");
        }else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El plataforma no existe");
        }
    }

    @GetMapping("/plataforma/{id}")
    public ResponseEntity<List<CanalesDTO>>listarPlataformaId(@PathVariable int id){
        ModelMapper m = new ModelMapper();

        List<CanalesDTO> lista = cS.findByPlataformaId(id).stream()
                .map(x -> m.map(x, CanalesDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PostMapping("/plataforma/{id}")
    public ResponseEntity<?> registrarPlataformaId(@PathVariable int id, @RequestBody CanalesDTO canalesDTO){
        ModelMapper m = new ModelMapper();
        Canales canal = m.map(canalesDTO, Canales.class);
        Canales nuevo = cS.insertPlataformaIdAndCanale(id, canal);

        if (nuevo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La plataforma no existe");
        }

        CanalesDTO dto = m.map(nuevo, CanalesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
