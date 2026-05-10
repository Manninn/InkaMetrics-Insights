package upc.edu.pe.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.tpbackinkametrics.dtos.MarcaDTO;
import upc.edu.pe.tpbackinkametrics.entities.Marca;
import upc.edu.pe.tpbackinkametrics.serviceinterfaces.IMarcaService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/marcas")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
public class MarcaController {
    @Autowired
    private IMarcaService maS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('CLIENTE')")
    public List<MarcaDTO> listar() {
        System.out.println("Entrando a listar Marca");
        return maS.list().stream()
                .map(entity -> {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(entity, MarcaDTO.class);
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void insertar(@RequestBody MarcaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Marca entity = modelMapper.map(dto, Marca.class);
        maS.insert(entity);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> actualizar(@RequestBody MarcaDTO dto) {
        Optional<Marca> existente = maS.listId(dto.getIdMarca());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca no encontrado");
        }
        Marca mar = existente.get();
        mar.setNombre(dto.getNombre());
        mar.setSector(dto.getSector());
        maS.update(mar);
        return ResponseEntity.ok("Marca actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Marca> marca = maS.listId(id);

        if (marca.isPresent()) {
            maS.delete(id);
            return ResponseEntity.ok("Marca eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca no encontrado");
        }
    }
}
