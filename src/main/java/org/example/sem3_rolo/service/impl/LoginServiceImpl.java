package org.example.sem3_rolo.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sem3_rolo.entity.UserEntity;
import org.example.sem3_rolo.repository.LoginRepository;
import org.example.sem3_rolo.service.LoginService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Override
    public boolean authenticateUser(String email, String password) {
        UserEntity user = loginRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }
}
