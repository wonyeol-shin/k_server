package com.server.coffee.domain.user.service;

import com.server.coffee.domain.user.entity.User;
import com.server.coffee.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;

    public void registerTmpUser() {
        List<User> users = List.of(User.create("BOB"), User.create("ALICE"), User.create("BENSON"));
        userRepository.saveAll(users);
    }
}
