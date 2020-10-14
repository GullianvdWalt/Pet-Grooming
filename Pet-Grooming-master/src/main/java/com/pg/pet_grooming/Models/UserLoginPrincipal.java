package com.pg.pet_grooming.Models;

// Imports
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserLoginPrincipal implements UserDetails {

    // UserLogin Model Object
    private UserLogin userLogin;

    // Constructor
    public UserLoginPrincipal(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    // Override Methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    // Getters
    // Get User Password
    @Override
    public String getPassword() {
        return userLogin.getPassword();
    }
    // Get Username

    @Override
    public String getUsername() {
        return userLogin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
