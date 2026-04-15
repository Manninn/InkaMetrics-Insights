package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.PlanesDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlanesService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Planes")
public class PlanesController {
    @Autowired
    private IPlanesService cS;
    @GetMapping
    public ResponseEntity<List<PlanesDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<PlanesDTO> listaPlanes=cS.list().stream()
                .map(x->m.map(x, PlanesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaPlanes);
    }
}
