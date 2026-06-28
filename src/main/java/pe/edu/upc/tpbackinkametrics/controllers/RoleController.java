package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.RoleDTO;
import pe.edu.upc.tpbackinkametrics.entities.Role;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IRoleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
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
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<RoleDTO> list() {
        return roleService.list().stream().map(x -> mO.map(x, RoleDTO.class)).collect(Collectors.toList());
    }

    @PutMapping("/update")
    public void update(@RequestBody RoleDTO dto) {
        roleService.update(mO.map(dto, Role.class));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        roleService.delete(id);
    }

    @GetMapping("/{id}")
    public RoleDTO findById(@PathVariable("id") Long id) {
        return mO.map(roleService.listId(id), RoleDTO.class);
    }
}
