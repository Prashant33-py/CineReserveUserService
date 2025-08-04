package com.cinereserve.app.CineReserveUserService.dto;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String userName;
    private String email;
    private String password;
    private String confirmPassword;

    public boolean isPasswordMatching() {
        return password != null && password.equals(confirmPassword);
    }
}
