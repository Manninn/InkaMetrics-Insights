package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.UserDTO;
import pe.edu.upc.tpbackinkametrics.entities.Users;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private IUserService uS;
    @Autowired
    private ModelMapper mO;

    @PostMapping("/nuevo")
    public void registrar(@RequestBody UserDTO dto) {
        uS.insert(mO.map(dto, Users.class));
    }

    @GetMapping("/lista")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<UserDTO> listar() {
        return uS.list().stream().map(x -> mO.map(x, UserDTO.class)).collect(Collectors.toList());
    }

    @PutMapping("/actualiza")
    public void modificar(@RequestBody UserDTO dto) {
        uS.update(mO.map(dto, Users.class));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        uS.delete(id);
    }

    @GetMapping("/{id}")
    public UserDTO buscarPorId(@PathVariable("id") Long id) {
        return mO.map(uS.listId(id), UserDTO.class);
    }
}
