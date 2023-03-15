package com.PFE.Espacecommercant.Authen.Controller;

import com.PFE.Espacecommercant.Authen.DTO.AuthenticationRequest;
import com.PFE.Espacecommercant.Authen.DTO.AuthenticationResponse;
import com.PFE.Espacecommercant.Authen.DTO.RegisterRequest;
import com.PFE.Espacecommercant.Authen.Service.AuthenticationService;
import com.PFE.Espacecommercant.Authen.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService service;

    @GetMapping("/{email}")
    public ResponseEntity<Optional<User>> findByEmail(@PathVariable String email) {
        Optional<User> Response=service.findByemail(email);
        return ResponseEntity.ok(Response);
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/registeradmin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerAdmin(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
