package com.example.weight_management_system.service.impl;

import com.example.weight_management_system.model.MUser;
import com.example.weight_management_system.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MUser loginUser = userService.getLoginUser(username);

        if (loginUser == null) {
            throw new UsernameNotFoundException("user not found");
        }

        String role = this.roleToReplaceValue(loginUser.getRoleCode());

        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        UserDetails userDetails = new User(loginUser.getEmail(), loginUser.getPassword(), authorities);
        return userDetails;
    }

    private String roleToReplaceValue(int role) {
        switch (role) {
            case 1:
                return "ADMIN";
            case 2:
                return "GENERAL";
            default:
                throw new IllegalArgumentException(String.format("invalid role: expected=1 or 2, actual=%s", role));
        }
    }
}
