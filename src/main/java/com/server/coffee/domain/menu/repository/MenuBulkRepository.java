package com.server.coffee.domain.menu.repository;

import com.server.coffee.domain.menu.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveBulkMenu(List<Menu> menus){

        String sql = "insert into menus(name,price,created_at) " +
                "values(?,?, NOW(6))";

        int batchSize = 1_000;
        int totalSize = menus.size();

        for (int i=0; i<totalSize; i+=batchSize){
            List<Menu> subMenus = menus.subList(i, Math.min(i+batchSize, totalSize));
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Menu menu = menus.get(i);
                    ps.setString(1, menu.getName());
                    ps.setInt(2, menu.getPrice());
                }

                @Override
                public int getBatchSize() {
                    return subMenus.size();
                }

            });
        }

    }
}
