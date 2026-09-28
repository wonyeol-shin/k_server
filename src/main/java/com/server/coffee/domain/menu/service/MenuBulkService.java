package com.server.coffee.domain.menu.service;

import com.server.coffee.domain.menu.entity.Menu;
import com.server.coffee.domain.menu.repository.MenuBulkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class MenuBulkService {

    private final MenuBulkRepository menuBulkRepository;

    public void createBulkMenu(){
        String[] flavor = {
               "저렴한","프리미엄", "고소한", "산미가 강한", "초콜릿티한", "바디감이 있는", "연한", "단맛이 있는"};

        String[] category = {
                "아메리카노","카페라떼", "카페모카", "카푸치노", "아샷추", "롱고", "에스프레소"
        };

        List<Menu> menuList = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i=0; i<=50_000; i++){
            String name = flavor[random.nextInt(flavor.length)] +
                    " " + category[random.nextInt(category.length)];
            int price = random.nextInt(1,10) * 1000;
            menuList.add(Menu.create(name, price));
        }

        menuBulkRepository.saveBulkMenu(menuList);

    }
}
