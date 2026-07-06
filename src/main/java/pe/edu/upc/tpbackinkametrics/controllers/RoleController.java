package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.RoleDTO;
import pe.edu.upc.tpbackinkametrics.entities.Role;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IRoleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ModelMapper mO;

    @PostMapping("/new")
    public void insert(@RequestBody RoleDTO dto) {
        roleService.insert(mO.map(dto, Role.class));
    }

    @GetMapping("/list")
    public List<RoleDTO> list() {
        return roleService.list().stream().map(x -> mO.map(x, RoleDTO.class)).collect(Collectors.toList());
    }

    @PutMapping("/update")
    public void update(@RequestBody RoleDTO dto) {
        roleService.update(mO.map(dto, Role.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            roleService.delete(id);
            return ResponseEntity.ok("Role deleted successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el rol porque tiene usuarios asociados.");
        }
    }

    @GetMapping("/{id}")
    public RoleDTO findById(@PathVariable("id") Long id) {
        return mO.map(roleService.listId(id), RoleDTO.class);
    }
}
