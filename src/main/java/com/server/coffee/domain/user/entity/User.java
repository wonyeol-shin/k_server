package com.server.coffee.domain.user.entity;

import com.server.coffee.common.entity.BaseTimeEntity;
import com.server.coffee.common.exception.BusinessException;
import com.server.coffee.common.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String nickname;

    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private int point;

    private User(String nickname) {
        this.nickname = nickname;
        this.point = 0;
    }

    public static User create(String nickname){
        return new User(nickname);
    }

    public void charge(int point) {
        if (point < 1_000 || point > 1_000_000) {
            throw new BusinessException(ErrorCode.INVALID_POINT_AMOUNT);
        }
        this.point += point;
    }

    public void use(int point) {
        if (this.point - point < 0) {
            throw new BusinessException(ErrorCode.INSUFFICIENT_POINT);
        }
        this.point -= point;
    }

}
