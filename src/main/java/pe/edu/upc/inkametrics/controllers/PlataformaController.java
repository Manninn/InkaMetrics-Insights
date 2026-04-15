package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics.dtos.PlataformaCreateDTO;
import pe.edu.upc.inkametrics.dtos.PlataformasDTO;
import pe.edu.upc.inkametrics.entities.Plataformas;
import pe.edu.upc.inkametrics.servicesinterfaces.IPlataformasService;


import java.util.List;
import java.util.Optional;
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

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoPlataforma(@RequestBody PlataformaCreateDTO plataformascreateDTO) {
        ModelMapper m = new ModelMapper();

        Plataformas p = m.map(plataformascreateDTO, Plataformas.class);

        Plataformas plt = cS.insert(p);

        PlataformasDTO response = m.map(plt, PlataformasDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/actualiza")
    public ResponseEntity<?>actualizarPlataforma(@RequestBody PlataformasDTO plataformasDTO) {
        ModelMapper m =new ModelMapper();
        Optional<Plataformas> pltExist = cS.ListId(plataformasDTO.getId_plataforma());

        if (pltExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El plataforma no existe");
        }

        Plataformas pt =  pltExist.get();
        pt.setNombre(plataformasDTO.getNombre());
        pt.setUrl_base(plataformasDTO.getUrl_base());

        Plataformas actualizado = cS.update(pt);
        PlataformasDTO dto = m.map(actualizado, PlataformasDTO.class);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPlataforma(@PathVariable int id) {
        Optional<Plataformas> plataformas = cS.ListId(id);

        if (plataformas.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Plataforma eliminada");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El plataforma no existe");
        }
    }
}
