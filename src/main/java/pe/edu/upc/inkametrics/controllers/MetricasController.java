package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.MetricasDTO;
import pe.edu.upc.inkametrics.dtos.MetricasPorTransmisionDTO;
import pe.edu.upc.inkametrics.entities.Metricas;
import pe.edu.upc.inkametrics.servicesinterfaces.IMetricasService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Metricas")
public class MetricasController {
    @Autowired
    private IMetricasService cS;
    @GetMapping
    public ResponseEntity<List<MetricasDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<MetricasDTO> listaMetricas=cS.list().stream()
                .map(x->m.map(x, MetricasDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaMetricas);
    }
    @PostMapping("/nueva")
    public ResponseEntity<?> registrar(@RequestBody MetricasDTO dto){ // @RequestBody transfiere la peticion para insertar la peticion
        ModelMapper m = new ModelMapper(); // modelmapper transforma el CourseDto a Course
        Metricas c = m.map(dto, Metricas.class);

        Metricas met = cS.insert(c);
        MetricasDTO responseDTO = m.map(met, MetricasDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) { //ResponseEntity permite gestionar la rpta http
        ModelMapper m = new ModelMapper();
        Optional<Metricas> metrica = cS.listId(id);

        if (metrica.isPresent()) {
            MetricasDTO dto = m.map(metrica.get(), MetricasDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Metrica no encontrada");
        }
    }

    @PutMapping("/actualiza") //es la ruta, solo es la url - nombre para mostrar que representa
    public ResponseEntity<String> actualizar(@RequestBody MetricasDTO dto) { // @RequestBody transfiere la peticion para actualizar la peticion

        Optional<Metricas> existente = cS.listId(dto.getIdMetricas());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Metrica no encontrada");
        }

        Metricas met = existente.get();

        met.setNameMetrica(dto.getNameMetrica());
        met.setCantidadMetrica(dto.getCantidadMetrica());
        cS.update(met);

        return ResponseEntity.ok("Metrica actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) { // @pathvariable indica que tiene una variable en la ruta ("/{id}")
        Optional<Metricas> metricas = cS.listId(id);

        if (metricas.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Curso eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("machine no encontrado");
        }
    }


// Query 1:  Top 5 transmisiones con mayor cantidad de una métrica específica:
    @GetMapping("/reporte-detallado")
    public List<Map<String, String>> obtenerReporteFormateado() {
        // 1. Llamada limpia al service
        List<Object[]> lista = cS.reporteMetricasJPQL();

        // 2. TODA LA LÓGICA DE DESARROLLO AQUÍ
        List<Map<String, String>> response = new ArrayList<>();

        for (Object[] fila : lista) {
            Map<String, String> map = new HashMap<>();

            // fila[0] es el título (String), fila[1] es la suma (Long/Number)
            map.put("transmision", String.valueOf(fila[0]));
            map.put("total", String.valueOf(fila[1]));

            response.add(map);
        }

        return response;
    }

}
