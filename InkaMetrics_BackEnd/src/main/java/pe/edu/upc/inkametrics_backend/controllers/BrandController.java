package pe.edu.upc.inkametrics_backend.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.inkametrics_backend.dtos.BrandDTO;
import pe.edu.upc.inkametrics_backend.serviceinterfaces.IBrandService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private IBrandService bS;

    @GetMapping("/listarmarca")
    public ResponseEntity<List<BrandDTO>> listar(){
        ModelMapper m=new ModelMapper();
        List<BrandDTO> listamarca=bS.list().stream()
                .map(x->m.map(x,BrandDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listamarca);
    }
}
