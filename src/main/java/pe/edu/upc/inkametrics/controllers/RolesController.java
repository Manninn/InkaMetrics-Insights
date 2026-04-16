package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.RegionesDTO;
import pe.edu.upc.inkametrics.dtos.RolesDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.IRegionesService;
import pe.edu.upc.inkametrics.servicesinterfaces.IRolesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Roles")
public class RolesController {
    @Autowired
    private IRolesService cS;
    @GetMapping
    public ResponseEntity<List<RolesDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<RolesDTO> listaRoles=cS.list().stream()
                .map(x->m.map(x, RolesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaRoles);
    }
}
