package com.example.demo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {

    @Autowired
    private JdbcTemplate jdbc;

    // CREATE：追加
    public void insert(SiteUserAddress address) {
        String sql = "INSERT INTO SiteUserAddress (ID, postNumber, address1, address2, address3, address4) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        jdbc.update(sql,
                address.getID(),
                address.getPostNumber(),
                address.getAddress1(),
                address.getAddress2(),
                address.getAddress3(),
                address.getAddress4()
        );
    }

    // READ：1件取得（addressID + ID）
    public SiteUserAddress getByAddressIdAndUserId( int userID,int addressID) {
        String sql = "SELECT * FROM SiteUserAddress WHERE addressID = ? AND ID = ?";
        List<SiteUserAddress> list = jdbc.query(sql,
                new BeanPropertyRowMapper<>(SiteUserAddress.class),
                addressID, userID);
        return list.isEmpty() ? null : list.get(0);
    }

    // READ：ユーザーIDから住所一覧取得（複数）
    public List<SiteUserAddress> getByUserId(int userID) {
        String sql = "SELECT * FROM SiteUserAddress WHERE ID = ?";
        try {
        	return jdbc.query(sql,
                    new BeanPropertyRowMapper<>(SiteUserAddress.class),
                    userID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // UPDATE：addressID + ID で更新
    public void update(SiteUserAddress address) {
        String sql = "UPDATE SiteUserAddress SET " +
                     "postNumber = ?, " +
                     "address1 = ?, " +
                     "address2 = ?, " +
                     "address3 = ?, " +
                     "address4 = ? " +
                     "WHERE addressID = ? AND ID = ?";
        jdbc.update(sql,
                address.getPostNumber(),
                address.getAddress1(),
                address.getAddress2(),
                address.getAddress3(),
                address.getAddress4(),
                address.getAddressID(),
                address.getID()
        );
    }

    // DELETE：addressID + ID で削除
    public void delete(int userID,int addressID) {
        String sql = "DELETE FROM SiteUserAddress WHERE addressID = ? AND ID = ?";
        jdbc.update(sql, addressID, userID);
    }
}

