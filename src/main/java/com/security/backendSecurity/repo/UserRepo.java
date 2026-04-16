package com.security.backendSecurity.repo;


import com.security.backendSecurity.model.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserDB, Long> {

    Optional<UserDB> findByUsername(String username);
}
