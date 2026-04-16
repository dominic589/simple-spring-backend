package com.security.backendSecurity.controller;

import com.security.backendSecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class controller {

    @GetMapping("/")
    public String cool(HttpServletRequest request) {
        return "cool" + request.getSession().getId();
    }

    private List<Student> studentlist = new ArrayList<>(List.of
            (new Student("aaron", 2, 34),
                    new Student("boyd", 7, 78)));

    @GetMapping("/student")
    public List<Student> getstudent() {
        return studentlist;
    }

    @PostMapping("/student")
    public Student addstudent(@RequestBody Student student) {
        studentlist.add(student);
        return student;
    }

    @GetMapping("/csrf")
    public CsrfToken getcsrf(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
