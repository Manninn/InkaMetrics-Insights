package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.MetricasDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.IMetricasService;

import java.util.List;
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

}
