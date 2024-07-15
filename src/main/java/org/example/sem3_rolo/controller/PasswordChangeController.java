package org.example.sem3_rolo.controller;

import lombok.RequiredArgsConstructor;
import org.example.sem3_rolo.pojo.PasswordChangeRequest;
import org.example.sem3_rolo.pojo.UserPojo;
import org.example.sem3_rolo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordChangeController {

    private final UserService userService;

    @PostMapping("/change")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequest changeRequest) {
        String email = changeRequest.getEmail();
        String newPassword = changeRequest.getNewPassword();



        UserPojo user = userService.findUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        if (!isValidPassword(newPassword)) {
            return ResponseEntity.badRequest().body("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }

        // Update user's password
        user.setPassword(newPassword);
        userService.saveData(user); // Update user's password

        return ResponseEntity.ok("Password changed successfully");
    }
    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[!@#$%^&*()].*");
    }
}

