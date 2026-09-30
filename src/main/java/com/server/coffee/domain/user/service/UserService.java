package com.server.coffee.domain.user.service;

import com.server.coffee.common.exception.BusinessException;
import com.server.coffee.common.exception.ErrorCode;
import com.server.coffee.domain.user.entity.User;
import com.server.coffee.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUser(String nickname) {
        return userRepository.findByNickname(nickname).orElseThrow(
                () -> new BusinessException(ErrorCode.NOT_FOUND_USER)
        );
    }

}
