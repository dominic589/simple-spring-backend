package com.security.backendSecurity.service;

import com.security.backendSecurity.model.UserDB;
import com.security.backendSecurity.model.loginUsers;
import com.security.backendSecurity.model.registerUser;
import com.security.backendSecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public String register(registerUser request) {

        UserDB user = new UserDB();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));

        repo.save(user);

        return "User registered successfully";
    }

    public UserDB save(UserDB user) {
        UserDB saved = repo.save(user);
        System.out.println(STR."Saved user ID: \{saved.getId()}");
        return saved;
    }

    public String verify(loginUsers user) {
        Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
       if(authenticate.isAuthenticated())
           return jwtService.generatedToken(user.getUsername());
       return "failed";
    }
}