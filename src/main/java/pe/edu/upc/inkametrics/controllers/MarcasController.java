package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.Empresas_clientesDTO;
import pe.edu.upc.inkametrics.dtos.MarcasDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.IEmpresas_clientesService;
import pe.edu.upc.inkametrics.servicesinterfaces.IMarcasService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Marcas")
public class MarcasController {
    @Autowired
    private IMarcasService cS;
    @GetMapping
    public ResponseEntity<List<MarcasDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<MarcasDTO> listaMarcas=cS.list().stream()
                .map(x->m.map(x, MarcasDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaMarcas);
    }

}
