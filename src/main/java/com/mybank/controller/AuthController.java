package com.mybank.controller;

import com.mybank.dto.LoginDTO;
import com.mybank.dto.UserDTO;
import com.mybank.dto.JwtResponseDTO;
import com.mybank.dto.JwtResponseDTO;
import com.mybank.entity.Role;
import com.mybank.entity.User;
import com.mybank.repository.RoleRepository;
import com.mybank.repository.UserRepository;
import com.mybank.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.mybank.model.LoginRequest;
import com.mybank.model.LoginResponse;
import com.mybank.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//AuthController — controllerul care gestionează înregistrarea și autentificarea utilizatorilor,
// generează token JWT și returnează rolul pentru controlul accesului.
@RestController
@RequestMapping("/auth") // toate endpointurile vor începe cu /auth
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    // ✅ Înregistrare utilizator
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Parolele nu coincid.");
        }

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username deja existent.");
        }

        Role role = roleRepository.findByName(dto.getRoleName());
        if (role == null) {
            return ResponseEntity.badRequest().body("Rol invalid.");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // parolele se criptează
        user.setFullname(dto.getFullname());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setRole(role);

        userRepository.save(user);
        return ResponseEntity.ok("Utilizator înregistrat cu succes.");
    }

    // ✅ Autentificare utilizator
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            // Autentifică utilizatorul
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Autentificare eșuată.");
        }

        // Caută utilizatorul și generează token
        Optional<User> userOpt = userRepository.findByUsername(dto.getUsername());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Utilizator inexistent.");
        }

        User user = userOpt.get();
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().getName()) // rolul pentru JWT
                .build();

        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDTO(token, user.getRole().getName()));
    }
}
