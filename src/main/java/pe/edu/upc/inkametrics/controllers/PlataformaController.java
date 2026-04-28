package pe.edu.upc.inkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> registrar(@RequestBody PlataformasDTO pltDTO) {
        ModelMapper m =new ModelMapper();
        Plataformas p=m.map(pltDTO,Plataformas.class);
        p.setId_plataforma(p.getId_plataforma());

        Plataformas plt = cS.insert(p);
        PlataformasDTO responseDto = m.map(plt,PlataformasDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/id")
    public ResponseEntity<?> buscarPorId(@PathVariable int id_plataforma) {
        ModelMapper m =new ModelMapper();
        Optional<Plataformas> plataformas = cS.ListById(id_plataforma);

        if(plataformas.isPresent()){
            PlataformasDTO dto =  m.map(plataformas.get(),PlataformasDTO.class);
            return ResponseEntity.ok(dto);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La plataforma no existe.");
        }
    }

    @PutMapping("/{id_plataforma}")
    public ResponseEntity<?> actualizar(@RequestBody PlataformasDTO pltDTO,
                                        @PathVariable("id_plataforma") int id_plataforma) {
        Optional<Plataformas> plataformasExistentes = cS.ListById(id_plataforma);

        if (plataformasExistentes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La plataforma no se encontro.");
        }
        Plataformas pl =  plataformasExistentes.get();
        pl.setNombre(pltDTO.getNombre());
        pl.setUrl_base(pltDTO.getUrl_base());

        cS.update(pl);
        return ResponseEntity.ok("Plataforma actualizada correctamente.");
    }

    @DeleteMapping("/{id_plataforma}")
    public ResponseEntity<String> eliminar(@PathVariable("id_plataforma") int id_plataforma) {
        Optional<Plataformas> plataformas = cS.ListById(id_plataforma);

        if (plataformas.isPresent()){
            cS.delete(id_plataforma);
            return ResponseEntity.ok("Plataforma eliminada.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La plataforma no econtrada.");
        }
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<PlataformasDTO>> buscarPorNombre(@PathVariable String nombre) {

        ModelMapper m = new ModelMapper();

        List<PlataformasDTO> lista = cS.buscarPorNombre(nombre).stream()
                .map(p -> m.map(p, PlataformasDTO.class))
                .toList();

        return ResponseEntity.ok(lista);
    }
}