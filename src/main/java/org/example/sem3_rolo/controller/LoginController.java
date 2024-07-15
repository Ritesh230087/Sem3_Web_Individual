package org.example.sem3_rolo.controller;

import lombok.RequiredArgsConstructor;
import org.example.sem3_rolo.pojo.UserPojo;
import org.example.sem3_rolo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody UserPojo loginRequest) {
        //for admin
        String adminUsername = "admin@gmail.com";
        String adminPassword = "Admin@123";

        if (loginRequest.getEmail().equals(adminUsername) && loginRequest.getPassword().equals(adminPassword)) {
            return ResponseEntity.ok("Admin login successful!");
        }

        UserPojo user = userService.findUserByEmail(loginRequest.getEmail());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful!");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");

    }
}
