package com.server.coffee.domain.point.service;

import com.server.coffee.domain.point.entity.PointHistory;
import com.server.coffee.domain.point.repository.PointHistoryRepository;
import com.server.coffee.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointHistoryService {

    private final PointHistoryRepository pointHistoryRepository;

    public void charge(User user, int point) {
        pointHistoryRepository.save(PointHistory.charge(point,user));
        user.charge(point);
    }
}
