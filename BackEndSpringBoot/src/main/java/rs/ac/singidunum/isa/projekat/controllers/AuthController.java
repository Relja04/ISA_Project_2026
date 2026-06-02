package rs.ac.singidunum.isa.projekat.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.projekat.dto.request.LoginRequest;
import rs.ac.singidunum.isa.projekat.dto.request.RegisterRequest;
import rs.ac.singidunum.isa.projekat.services.UserService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

}