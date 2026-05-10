package upc.edu.pe.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.tpbackinkametrics.dtos.PlataformaDTO;
import upc.edu.pe.tpbackinkametrics.entities.Plataforma;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IPlataformaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plataformas")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class PlataformaController {
    @Autowired
    private IPlataformaService plS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<PlataformaDTO> listar() {
        System.out.println("Entrando a listar Plataforma");
        return plS.list().stream()
                .map(entity -> {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(entity, PlataformaDTO.class);
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void insertar(@RequestBody PlataformaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Plataforma entity = modelMapper.map(dto, Plataforma.class);
        plS.insert(entity);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> actualizar(@RequestBody PlataformaDTO dto) {
        Optional<Plataforma> existente = plS.listId(dto.getIdPlataforma());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plataforma no encontrado");
        }
        Plataforma plat = existente.get();
        plat.setNombre(dto.getNombre());
        plat.setUrlBase(dto.getUrlBase());
        plS.update(plat);
        return ResponseEntity.ok("Plataforma actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Plataforma> plataforma = plS.listId(id);

        if (plataforma.isPresent()) {
            plS.delete(id);
            return ResponseEntity.ok("Plataforma eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plataforma no encontrado");
        }
    }
}
