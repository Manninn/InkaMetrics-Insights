package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.CanalesDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.ICanalesService;

import java.util.List;
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

}
