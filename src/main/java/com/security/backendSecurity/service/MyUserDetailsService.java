package com.security.backendSecurity.service;

import com.security.backendSecurity.model.UserDB;
import com.security.backendSecurity.model.UserPrincipal;
import com.security.backendSecurity.model.loginUsers;
import com.security.backendSecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepo repo;

    public MyUserDetailsService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserDB> user = repo.findByUsername(username);
        if(user.isEmpty()) {
            System.out.println("user Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrincipal(user.get());
    }
}
