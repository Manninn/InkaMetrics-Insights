package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.StreamersDTO;
import pe.edu.upc.inkametrics.dtos.TransmisionesDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.IStreamersService;
import pe.edu.upc.inkametrics.servicesinterfaces.ITransmisionesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Transmisiones")
public class TransmisionesController {
    @Autowired
    private ITransmisionesService cS;
    @GetMapping
    public ResponseEntity<List<TransmisionesDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<TransmisionesDTO> listaTransmisiones=cS.list().stream()
                .map(x->m.map(x, TransmisionesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaTransmisiones);
    }
}
