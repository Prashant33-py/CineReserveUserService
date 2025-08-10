package com.cinereserve.app.CineReserveUserService.service;

import com.cinereserve.app.CineReserveUserService.dto.LoginDTO;
import com.cinereserve.app.CineReserveUserService.dto.RegisterUserDTO;
import com.cinereserve.app.CineReserveUserService.model.CineUser;
import com.cinereserve.app.CineReserveUserService.repository.CineUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class CineUserService {

    private final CineUserRepository cineUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public CineUserService(CineUserRepository cineUserRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.cineUserRepository = cineUserRepository;
        this.passwordEncoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public ResponseEntity<String> register(RegisterUserDTO user) {
        if (!user.isPasswordMatching()) {
            return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
        }
        CineUser newUser = CineUser.builder()
                .email(user.getEmail())
                .userName(user.getUserName())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(CineUser.Role.USER)
                .build();

        cineUserRepository.save(newUser);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<CineUser>> getUsers() {
        return new ResponseEntity<>(cineUserRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> login(String authHeader) {
        String[] authParts = new String(Base64.getDecoder().decode(authHeader)).split(":");
        LoginDTO loginUser = new LoginDTO(authParts[0], authParts[1]);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginUser.getUserName(), authentication.getAuthorities());
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }
}
