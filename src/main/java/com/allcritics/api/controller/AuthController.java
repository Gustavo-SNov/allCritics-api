package com.allcritics.api.controller;

import com.allcritics.api.dto.user.LoginDTO;
import com.allcritics.api.dto.user.RegisterDTO;
import com.allcritics.api.dto.user.UserDTO;
import com.allcritics.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    private AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody LoginDTO login){
        UserDTO userDTO = userService.login(login);

        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterDTO register){

        UserDTO userDTO = userService.register(register);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }
}
