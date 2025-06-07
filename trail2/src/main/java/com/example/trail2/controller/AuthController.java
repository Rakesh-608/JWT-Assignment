package com.example.trail2.controller;

import com.example.trail2.dto.ApiResponse;
import com.example.trail2.dto.AuthRequest;
import com.example.trail2.dto.AuthResponse;
import com.example.trail2.model.User;
import com.example.trail2.repository.UserRepository;
import com.example.trail2.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                          UserDetailsService userDetailsService, UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.username());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody AuthRequest authRequest) {
        if (userRepository.existsById(authRequest.username())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Username already exists"));
        }

        User user = new User(authRequest.username(),
                passwordEncoder.encode(authRequest.password()),
                "ROLE_USER");
        userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse("User registered successfully"));
    }
}



