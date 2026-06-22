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
    private IRoleService rS;
    @Autowired
    private ModelMapper mO;

    @PostMapping("/nuevo")
    public void registrar(@RequestBody RoleDTO dto) {
        rS.insert(mO.map(dto, Role.class));
    }

    @GetMapping("/lista")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<RoleDTO> listar() {
        return rS.list().stream().map(x -> mO.map(x, RoleDTO.class)).collect(Collectors.toList());
    }

    @PutMapping("/actualiza")
    public void modificar(@RequestBody RoleDTO dto) {
        rS.update(mO.map(dto, Role.class));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        rS.delete(id);
    }

    @GetMapping("/{id}")
    public RoleDTO buscarPorId(@PathVariable("id") Long id) {
        return mO.map(rS.listId(id), RoleDTO.class);
    }
}
