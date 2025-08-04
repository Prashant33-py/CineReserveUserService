package com.cinereserve.app.CineReserveUserService.service;

import com.cinereserve.app.CineReserveUserService.model.CineUser;
import com.cinereserve.app.CineReserveUserService.model.CineUserPrincipal;
import com.cinereserve.app.CineReserveUserService.repository.CineUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CineUserDetailsService implements UserDetailsService {

    @Autowired
    private CineUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CineUser currentUser = userRepository.findByUserName(username);
        if (currentUser == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CineUserPrincipal(currentUser);
    }
}
