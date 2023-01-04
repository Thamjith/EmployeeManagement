package com.inapp.EmployeeManagement.Service;

import com.inapp.EmployeeManagement.Config.JwtService;
import com.inapp.EmployeeManagement.Controller.AuthenticationRequest;
import com.inapp.EmployeeManagement.Controller.AuthenticationResponse;
import com.inapp.EmployeeManagement.Controller.RegisterRequest;
import com.inapp.EmployeeManagement.Entity.Role;
import com.inapp.EmployeeManagement.Entity.User;
import com.inapp.EmployeeManagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        log.info("Request == {}", request);
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        log.info("user == {}", user);
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        log.info("jwtToken == {}", jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Request == {}{}", request.getEmail(), request.getPassword());
        var test = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        log.info("test = {}", test);
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        log.info("user = {}", user);
        var jwtToken = jwtService.generateToken(user);
        log.info("jwtToken = {}", jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
