package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics.dtos.Empresas_clientesDTO;
import pe.edu.upc.inkametrics.servicesinterfaces.IEmpresas_clientesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Empresas_clientes")
public class Empresas_clientesController {
    @Autowired
    private IEmpresas_clientesService cS;
    @GetMapping
    public ResponseEntity<List<Empresas_clientesDTO>> listar() {
        ModelMapper m =new ModelMapper();
        List<Empresas_clientesDTO> listaEmpresas_clientes=cS.list().stream()
                .map(x->m.map(x, Empresas_clientesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaEmpresas_clientes);
    }

}
