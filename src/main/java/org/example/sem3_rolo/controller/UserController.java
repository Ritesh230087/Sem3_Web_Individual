package org.example.sem3_rolo.controller;

import lombok.RequiredArgsConstructor;
import org.example.sem3_rolo.entity.UserEntity;
import org.example.sem3_rolo.pojo.UserPojo;
import org.example.sem3_rolo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody UserPojo userPojo) {
        if (userService.findUserByEmail(userPojo.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        if (!userPojo.getEmail().endsWith("@gmail.com")) {
            throw new IllegalArgumentException("Email must end with @gmail.com");
        }
        String password = userPojo.getPassword();
        if (password.length() < 8 ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*[0-9].*") ||
                !password.matches(".*[!@#$%^&*()].*")) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }
        this.userService.saveData(userPojo);
        return ResponseEntity.ok("User registered successfully!");
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }



    @GetMapping("/list")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/list/{id}")
    public Optional<UserEntity> getUsersById(@PathVariable Integer id) {
        return userService.getUsersById(id);
    }

    @DeleteMapping("/list/{id}")
    public void deleteUsers(@PathVariable Integer id) {
        userService.deleteUsers(id);
    }
}
