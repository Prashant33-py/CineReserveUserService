package com.cinereserve.app.CineReserveUserService.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CineUserPrincipal implements UserDetails {

    private final CineUser cineUser;

    public CineUserPrincipal(CineUser cineUser) {
        this.cineUser = cineUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (cineUser.getRole() == CineUser.Role.ADMIN) {
            return List.of(new SimpleGrantedAuthority(CineUser.Role.ADMIN.name()), new SimpleGrantedAuthority(CineUser.Role.USER.name()));
        }
        else {
            return List.of(new SimpleGrantedAuthority(CineUser.Role.USER.name()));
        }
    }

    @Override
    public String getPassword() {
        return cineUser.getPassword();
    }

    @Override
    public String getUsername() {
        return cineUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
