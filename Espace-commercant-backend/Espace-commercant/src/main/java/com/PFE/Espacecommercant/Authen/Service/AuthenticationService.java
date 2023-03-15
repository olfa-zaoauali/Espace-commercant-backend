package com.PFE.Espacecommercant.Authen.Service;

import com.PFE.Espacecommercant.Authen.DTO.AuthenticationRequest;
import com.PFE.Espacecommercant.Authen.DTO.AuthenticationResponse;
import com.PFE.Espacecommercant.Authen.DTO.RegisterRequest;
import com.PFE.Espacecommercant.Authen.Repository.AdminRepository;
import com.PFE.Espacecommercant.Authen.Repository.UserRepository;
import com.PFE.Espacecommercant.Authen.users.Admin;
import com.PFE.Espacecommercant.Authen.users.Role;
import com.PFE.Espacecommercant.Authen.users.User;
import com.PFE.Espacecommercant.Authen.users.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final AdminRepository repoadmin ;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user= User.builder()
                //.firstname(request.getFirstname())
               // .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        repository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        var admin= Admin.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .domain(request.getDomain())
                .company(request.getCompany())
                .telephone(request.getTelephone())
                .matricule(request.getMatricule())
                .batinda(request.getBatinda())
                .logo(request.getLogo())
                .build();
        repoadmin.save(admin);
        User user  = UserMapper.toUser(admin);
        repository.save(user);
        var jwtToken=jwtService.generateToken(admin);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
         authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
         var user = repository.findByEmail(request.getEmail()).orElseThrow();
         var jwtToken=jwtService.generateToken(user);
         return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public Optional<User> findByemail(String email) {
        Optional<User> UserEntity= repository.findByEmail(email);
        return UserEntity;
    }
}
