package pe.edu.upc.tpbackinkametrics.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.dtos.UserDTO;
import pe.edu.upc.tpbackinkametrics.entities.User;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public List<UserDTO> list() {
        return userService.list().stream()
                .map(x -> new ModelMapper().map(x, UserDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    public void insert(@RequestBody UserDTO dto) {
        userService.insert(new ModelMapper().map(dto, User.class));
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody UserDTO dto) {
        User existing = userService.listId(dto.getId());
        if (existing == null || existing.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.update(new ModelMapper().map(dto, User.class));
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        User existing = userService.listId(id);
        if (existing == null || existing.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        try {
            userService.delete(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el usuario porque tiene roles asociados.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        User user = userService.listId(id);
        if (user == null || user.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(new ModelMapper().map(user, UserDTO.class));
    }
}
