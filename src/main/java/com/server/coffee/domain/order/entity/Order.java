package com.server.coffee.domain.order.entity;

import com.server.coffee.common.entity.BaseTimeEntity;
import com.server.coffee.domain.menu.entity.Menu;
import com.server.coffee.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="menu_id")
    private Menu menu;

    private Order(int price, User user, Menu menu, OrderType type) {
        this.price = price;
        this.user = user;
        this.menu = menu;
        this.type = type;
    }

    public static Order complete(int price, User user, Menu menu) {
        return new Order(price, user, menu, OrderType.COMPLETE);
    }
}
