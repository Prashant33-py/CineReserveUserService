package com.cinereserve.app.CineReserveUserService.controller;

import com.cinereserve.app.CineReserveUserService.dto.RegisterUserDTO;
import com.cinereserve.app.CineReserveUserService.model.CineUser;
import com.cinereserve.app.CineReserveUserService.service.CineUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cine-reserve/user")
public class CineUserController {

    private final CineUserService cineUserService;

    public CineUserController(CineUserService cineUserService) {
        this.cineUserService = cineUserService;
    }

    @PostMapping("login")
    public ResponseEntity<String> login(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization").substring(6);
        return cineUserService.login(authHeader);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterUserDTO user) {
        return cineUserService.register(user);
    }

    @GetMapping("users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CineUser>> getUsers() {
        return cineUserService.getUsers();
    }

}
