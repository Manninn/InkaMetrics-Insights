package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.RolesDTO;
import pe.edu.upc.inkametrics.dtos.StreamersDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.IRolesService;
import pe.edu.upc.inkametrics.servicesinterfaces.IStreamersService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Streamers")
public class StreamersController {
    @Autowired
    private IStreamersService cS;
    @GetMapping
    public ResponseEntity<List<StreamersDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<StreamersDTO> listaStreamers=cS.list().stream()
                .map(x->m.map(x, StreamersDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaStreamers);
    }
}
