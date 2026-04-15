package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.Canales_monitoreadosDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.ICanales_monitoreadosService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Canales_monitoreados")
public class Canales_monitoreadosController {
    @Autowired
    private ICanales_monitoreadosService cS;
    @GetMapping
    public ResponseEntity<List<Canales_monitoreadosDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<Canales_monitoreadosDTO> listaCanales_monitoreados=cS.list().stream()
                .map(x->m.map(x, Canales_monitoreadosDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaCanales_monitoreados);
    }
}
