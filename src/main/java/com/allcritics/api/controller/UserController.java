package com.allcritics.api.controller;

import com.allcritics.api.dto.user.UserDTO;
import com.allcritics.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> userDTOS = userService.getAllUsers();
        return ResponseEntity.ok().body(userDTOS);
    }

    @GetMapping(value = "/{idUser}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String idUser) {
        UserDTO userDTO = userService.getUserById(idUser);
        return ResponseEntity.ok().body(userDTO);
    }

    @DeleteMapping(value = "/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable String idUser) {
        userService.deleteUserById(idUser);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idUser}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String idUser, @Valid @RequestBody UserDTO userDTO, Authentication authentication) {
        System.out.println(userDTO.toString());
        String username = authentication.getName();

        UserDTO updatedUser = userService.updateUser(idUser,userDTO,username);
        return ResponseEntity.ok().body(updatedUser);
    }
}
