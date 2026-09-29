package com.server.coffee.domain.point.service;

import com.server.coffee.domain.user.entity.User;
import com.server.coffee.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PointChargeService {
    private final UserService userService;
    private final PointHistoryService pointHistoryService;

    public void findUserAndCharge(String nickname, int point) {
        User user = userService.findUser(nickname);
        pointHistoryService.charge(user, 정point);
    }
}
