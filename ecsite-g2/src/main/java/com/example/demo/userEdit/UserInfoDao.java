package com.example.demo.userEdit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.user.SiteUserInfo;

@Repository
public class UserInfoDao {

    private final JdbcTemplate jdbc;

    public UserInfoDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // 追加
    public void insert(SiteUserInfo info) {
        String sql = "INSERT INTO SiteUserInfo (ID, gender, creditNumber, birthday, firstName1, lastName1, firstName2, lastName2) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(sql,
            info.getID(),
            info.getGender(),
            info.getCreditNumber(),
            info.getBirthday(),
            info.getFirstName1(),
            info.getLastName1(),
            info.getFirstName2(),
            info.getLastName2()
        );
    }

    // 取得
    public SiteUserInfo getById(int id) {
        String sql = "SELECT * FROM SiteUserInfo WHERE ID = ?";
        try {
            return jdbc.queryForObject(sql, mapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // 更新
 // 更新整个对象（ID必须存在）
    public void update(SiteUserInfo info) {
    	if(getById(info.getID())==null) {
    		insert(info);
    		return;
    	};
    	
        String sql = "UPDATE SiteUserInfo SET " +
                "gender = ?, " +
                "creditNumber = ?, " +
                "birthday = ?, " +
                "firstName1 = ?, " +
                "lastName1 = ?, " +
                "firstName2 = ?, " +
                "lastName2 = ? " +
                "WHERE ID = ?";

        jdbc.update(sql,
            info.getGender(),
            info.getCreditNumber(),
            info.getBirthday(),
            info.getFirstName1(),
            info.getLastName1(),
            info.getFirstName2(),
            info.getLastName2(),
            info.getID()
        );
    }


    // 削除
    public void delete(int id) {
        String sql = "DELETE FROM SiteUserInfo WHERE ID = ?";
        jdbc.update(sql, id);
    }

    // 全件取得
    public List<SiteUserInfo> getAll() {
        String sql = "SELECT * FROM SiteUserInfo";
        return jdbc.query(sql, mapper);
    }

    // 共通RowMapper
    private final RowMapper<SiteUserInfo> mapper = new RowMapper<SiteUserInfo>() {
        @Override
        public SiteUserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            SiteUserInfo info = new SiteUserInfo();
            info.setID(rs.getInt("ID"));
            info.setGender(rs.getString("gender"));
            info.setCreditNumber(rs.getString("creditNumber"));
            info.setBirthday(rs.getString("birthday")); // 型注意：必要なら java.sql.Date に変えてください
            info.setFirstName1(rs.getString("firstName1"));
            info.setLastName1(rs.getString("lastName1"));
            info.setFirstName2(rs.getString("firstName2"));
            info.setLastName2(rs.getString("lastName2"));
            return info;
        }
    };
}
