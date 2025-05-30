package com.example.demo.shop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDao {

    private final JdbcTemplate jdbcTemplate;

    public ItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Item> rowMapper = new RowMapper<Item>() {
        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item = new Item();
            item.setId(rs.getInt("item_id"));
            item.setName(rs.getString("name"));
            item.setPrice(rs.getInt("price"));
            item.setStock(rs.getInt("stock"));
            item.setImage(rs.getString("image"));
            item.setSizes(rs.getString("sizes"));
            return item;
        }
    };


    // 全件取得
    public List<Item> findAll() {
        String sql = "SELECT * FROM item";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // IDによる1件取得
    public Optional<Item> findById(Integer id) {
        String sql = "SELECT * FROM item WHERE item_id = ?";
        List<Item> result = jdbcTemplate.query(sql, rowMapper, id);
        return result.stream().findFirst();
    }
    
}
