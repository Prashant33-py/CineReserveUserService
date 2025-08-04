package com.cinereserve.app.CineReserveUserService.controller;

import com.cinereserve.app.CineReserveUserService.dto.LoginDTO;
import com.cinereserve.app.CineReserveUserService.dto.RegisterUserDTO;
import com.cinereserve.app.CineReserveUserService.model.CineUser;
import com.cinereserve.app.CineReserveUserService.service.CineUserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> login(@RequestBody LoginDTO loginUser) {
        return cineUserService.login(loginUser);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterUserDTO user) {
        return cineUserService.register(user);
    }

    @GetMapping("users")
    public ResponseEntity<List<CineUser>> getUsers() {
        return cineUserService.getUsers();
    }

}
