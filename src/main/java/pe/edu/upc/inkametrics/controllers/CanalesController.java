package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.CanalesDTO;
import pe.edu.upc.inkametrics.entities.Canales;
import pe.edu.upc.inkametrics.entities.Plataformas;
import pe.edu.upc.inkametrics.repositories.ICanalesRepository;
import pe.edu.upc.inkametrics.repositories.IPlanesRepository;
import pe.edu.upc.inkametrics.repositories.IPlataformasRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.ICanalesService;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlanesService;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlataformasService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Canales") // importante buscar en postman, con las mismas mayusculas
public class CanalesController {
    @Autowired
    private ICanalesService cS;
    @Autowired
    private IPlataformasService ipS;
    @Autowired
    private IPlataformasRepository iRPS;
    @Autowired
    private ICanalesRepository icRS;

    @GetMapping
    public ResponseEntity<?>listar() {
        ModelMapper m = new ModelMapper();

        List<CanalesDTO> lista = cS.list().stream().map(c -> {
            CanalesDTO dto = m.map(c, CanalesDTO.class);

            if (c.getPlataforma() != null) {
                dto.setId_plataforma(c.getPlataforma().getId_plataforma());
            }

            return dto;
        }).toList();

        return ResponseEntity.ok(lista);
    }

    @PostMapping("/canalesregistro")
    public ResponseEntity<?> crearCanale(@RequestBody CanalesDTO canalesDTO) {
        ModelMapper m = new ModelMapper();

        Optional<Plataformas> plataformas = ipS.ListById(canalesDTO.getId_plataforma());
        if (plataformas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plataforma no existe");
        }

        Canales cn = m.map(canalesDTO, Canales.class);
        cn.setPlataforma(plataformas.get());
        Canales guardado = cS.insert(cn);

        CanalesDTO dto = m.map(guardado, CanalesDTO.class);
        dto.setId_plataforma(guardado.getPlataforma().getId_plataforma());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCanales(@PathVariable int id, @RequestBody CanalesDTO canalesDTO) {
        Optional<Canales>canales =  cS.listCanales(id);
        if (canales.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron Canales en la BD.");
        }

        Optional<Plataformas>plataformas=ipS.ListById(canalesDTO.getId_plataforma());
        if (plataformas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plataforma no existe.");
        }

        Canales cn = canales.get();
        cn.setUrl_canal(canalesDTO.getUrl_canal());
        cn.setSeguidores_actuales(canalesDTO.getSeguidores_actuales());
        cn.setPlataforma(plataformas.get());
        cS.update(cn);
        return ResponseEntity.ok("Canal actualizado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCanales(@PathVariable int id) {
        Optional<Canales>canales =  cS.listCanales(id);
        if (canales.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron Canales en la BD.");
        }

        cS.deleteById(id);
        return ResponseEntity.ok("Canal eliminado.");
    }

    @GetMapping("/plataforma/{id}")
    public ResponseEntity<List<CanalesDTO>> listarPorPlataforma(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        List<CanalesDTO> lista = cS.findByPlatformId(id).stream().map(c -> {
            CanalesDTO dto = m.map(c, CanalesDTO.class);
            dto.setId_plataforma(c.getPlataforma().getId_plataforma());
            return dto;
        }).toList();

        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/seguidores/{min}")
    public ResponseEntity<List<CanalesDTO>> buscarPorSeguidores(@PathVariable int min) {
        ModelMapper m = new ModelMapper();

        List<CanalesDTO>lista = cS.buscarPorSeguidores(min).stream()
                .map(c->{
                    CanalesDTO dto = m.map(c, CanalesDTO.class);
                    dto.setId_plataforma(c.getPlataforma().getId_plataforma());
                    return dto;
                }).toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/reporte/canales-por-plataforma")
    public ResponseEntity<List<Map<String, Object>>> contarCanalesPorPlataforma() {

        List<Map<String, Object>> respuesta = cS.contarCanalesPorPlataforma().stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("plataforma", obj[0]);
            map.put("cantidad_canales", obj[1]);
            return map;
        }).toList();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/reporte/promedio-seguidores")
    public ResponseEntity<List<Map<String, Object>>> promedioSeguidores() {

        List<Map<String, Object>> respuesta = cS.promedioSeguidoresPorPlataforma().stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("plataforma", obj[0]);
            map.put("promedio_seguidores", obj[1]);
            return map;
        }).toList();

        return ResponseEntity.ok(respuesta);
    }
}