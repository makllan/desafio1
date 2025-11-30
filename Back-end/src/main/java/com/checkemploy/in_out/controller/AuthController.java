package com.checkemploy.in_out.controller;

import com.checkemploy.in_out.dto.receive.LoginDTO;
import com.checkemploy.in_out.services.Services;
import com.checkemploy.in_out.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // Importante para o Frontend acessar
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.ok(service.login(loginDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
