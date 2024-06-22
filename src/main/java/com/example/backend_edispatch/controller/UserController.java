package com.example.backend_edispatch.controller;

import com.example.backend_edispatch.dto.UserDTO;
import com.example.backend_edispatch.model.entity.User;
import com.example.backend_edispatch.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, construir un mensaje de error apropiado
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getFieldErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append(". "));

                // Devolver una respuesta con el mensaje de error y estado 400
                return ResponseEntity.badRequest().body(errorMessage.toString());
            }

            // Aquí crear el usuario y devolver una respuesta 201 (Created)
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setRole(userDTO.getRole());

            // Lógica para crear el usuario en el servicio userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }catch (Exception e){
            System.out.println("----------------------------------------------1");
            return ResponseEntity.status(400).body(userDTO);
        }
    }
    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
