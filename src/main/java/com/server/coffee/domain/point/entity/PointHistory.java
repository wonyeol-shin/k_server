package com.server.coffee.domain.point.entity;

import com.server.coffee.common.entity.BaseTimeEntity;
import com.server.coffee.domain.order.entity.Order;
import com.server.coffee.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "point_historys")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PointType type;

    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private PointHistory(PointType type, int amount, User user, Order order) {
        this.type = type;
        this.amount = amount;
        this.user = user;
        this.order = order;
    }

    public static PointHistory charge(int amount, User user) {
        return new PointHistory(PointType.CHARGE, amount, user, null);
    }

    public static PointHistory use(int amount, User user, Order order) {
        return new PointHistory(PointType.USED, amount, user, order);
    }

}
