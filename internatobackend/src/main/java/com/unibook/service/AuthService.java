package com.unibook.service;

import com.unibook.dto.*;
import com.unibook.entity.User;
import com.unibook.repository.UserRepository;
import com.unibook.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email già registrata");
        }

        User user = User.builder()
                .nome(request.getNome())
                .cognome(request.getCognome())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .ruolo(User.Ruolo.STUDENTE) // ruolo default
                .attivo(true)
                .build();

        userRepository.save(user);

        String token = jwtUtils.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(),
                user.getRuolo().name(), user.getNome(), user.getCognome());
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        String token = jwtUtils.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(),
                user.getRuolo().name(), user.getNome(), user.getCognome());
    }
}