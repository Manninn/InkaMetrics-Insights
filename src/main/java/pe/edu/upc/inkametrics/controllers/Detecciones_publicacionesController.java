package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.Detecciones_publicitariasDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.IDetecciones_publicitariasService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Detecciones_publicaciones")
public class Detecciones_publicacionesController {
    @Autowired
    private IDetecciones_publicitariasService cS;
    @GetMapping
    public ResponseEntity<List<Detecciones_publicitariasDTO>>listar() {
        ModelMapper m =new ModelMapper();
        List<Detecciones_publicitariasDTO> listaDetecciones_publicitarias=cS.list().stream()
                .map(x->m.map(x, Detecciones_publicitariasDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDetecciones_publicitarias);
    }

}
