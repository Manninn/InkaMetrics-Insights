package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.TransmisionesDTO;
import pe.edu.upc.inkametrics.dtos.UsuariosDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.ITransmisionesService;
import pe.edu.upc.inkametrics.servicesinterfaces.IUsuariosService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Usuarios")
public class UsuariosController {
    @Autowired
    private IUsuariosService cS;
    @GetMapping
    public ResponseEntity<List<UsuariosDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<UsuariosDTO> listaUsuarios=cS.list().stream()
                .map(x->m.map(x, UsuariosDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUsuarios);
    }
}
