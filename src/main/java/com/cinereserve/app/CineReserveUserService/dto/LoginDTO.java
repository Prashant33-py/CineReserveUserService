package com.cinereserve.app.CineReserveUserService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
    private String userName;
    private String password;
}
