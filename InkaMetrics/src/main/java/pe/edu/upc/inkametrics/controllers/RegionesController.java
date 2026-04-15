package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.PlataformasDTO;
import pe.edu.upc.inkametrics.dtos.RegionesDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlataformasService;
import pe.edu.upc.inkametrics.servicesinterfaces.IRegionesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Regiones")
public class RegionesController {
    @Autowired
    private IRegionesService cS;
    @GetMapping
    public ResponseEntity<List<RegionesDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<RegionesDTO> listaRegiones=cS.list().stream()
                .map(x->m.map(x, RegionesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaRegiones);
    }
}
