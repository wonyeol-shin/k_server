가package com.server.coffee.domain.point.service;

import com.server.coffee.common.exception.BusinessException;
import com.server.coffee.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PointHistoryLockService {

    private final RedissonClient redissonClient;
    private final PointHistoryFacade pointHistoryFacade;

    public void charge(String nickname, int point){
        RLock lock = redissonClient.getLock("lock:point:" + nickname);

        boolean acquired = false;
        try{
            // 3초안에 lock을 얻으면 true
            acquired = lock.tryLock(3, TimeUnit.SECONDS);
            // 3초동 lock 얻지 못하면 예외
            if(!acquired){
                throw new BusinessException(ErrorCode.TIMEOUT_EXCEPTION);
            }
            // 로직 수행
            pointHistoryFacade.findUserAndCharge(nickname, point);

        } catch (InterruptedException e) {
            // 락 대기 중 스레드가 중단됨 (서버 종료 등)
            Thread.currentThread().interrupt();
            throw new BusinessException(ErrorCode.TIMEOUT_EXCEPTION);
        }finally {
            if(acquired && lock.isHeldByCurrentThread()){
                // 락을 잡고있을때만 lock 해제
                lock.unlock();
            }
        }

    }
}
