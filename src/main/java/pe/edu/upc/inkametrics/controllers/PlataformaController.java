package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.PlataformasDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlataformasService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Plataforma")
public class PlataformaController {
    @Autowired
    private IPlataformasService cS;
    @GetMapping
    public ResponseEntity<List<PlataformasDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<PlataformasDTO> listaPlataforma=cS.list().stream()
                .map(x->m.map(x, PlataformasDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaPlataforma);
    }
}
