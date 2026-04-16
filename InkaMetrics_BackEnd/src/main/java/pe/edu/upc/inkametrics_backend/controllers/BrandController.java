package pe.edu.upc.inkametrics_backend.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inkametrics_backend.dtos.BrandDTO;
import pe.edu.upc.inkametrics_backend.entities.Brand;
import pe.edu.upc.inkametrics_backend.serviceinterfaces.IBrandService;

import java.util.List;
import java.util.Optional;
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
    @PostMapping("/registrarmarca")
    public ResponseEntity<?> registrar(@RequestBody BrandDTO dto){
        ModelMapper m = new ModelMapper();
        Brand b = m.map(dto, Brand.class);
        Brand brandGuardada = bS.insert(b);
        BrandDTO responseDTO = m.map(brandGuardada, BrandDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualizarmarca")
    public ResponseEntity<String> actualizar(@RequestBody BrandDTO dto) {
        Optional<Brand> existente = bS.listId(dto.getIdBrand());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca no encontrado");
        }
        Brand bra = existente.get();
        bra.setIdBrand(dto.getIdBrand());
        bra.setBrandName(dto.getBrandName());
        bra.setSectorBrand(dto.getSectorBrand());
        bS.insert(bra);
        return ResponseEntity.ok("Marca actualizado correctamente");

    }

    @DeleteMapping("/borrarmarca/{idBrand}")
    public ResponseEntity<String> eliminar(@PathVariable int idBrand) {
        Optional<Brand> marca = bS.listId(idBrand);

        if (marca.isPresent()) {
            bS.delete(idBrand);
            return ResponseEntity.ok("Marca eliminado correctamenta");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca no encontrada");
        }

    }}
