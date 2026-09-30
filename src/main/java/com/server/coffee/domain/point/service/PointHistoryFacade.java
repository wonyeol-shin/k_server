package com.server.coffee.domain.point.service;

import com.server.coffee.common.lock.LockDistributeService;
import com.server.coffee.common.lock.LockKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointHistoryFacade {

    private final PointChargeService pointChargeService;
    private final LockDistributeService lockDistributeService;

    public void charge(String nickname, int point) {
        lockDistributeService.execute(
                LockKey.point(nickname),
                () -> pointChargeService.findUserAndCharge(nickname, point)
        );
    }
}
