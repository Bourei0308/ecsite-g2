package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.user.SiteUser;

@Repository
public class Dao {
    private final JdbcTemplate db;

    public Dao(JdbcTemplate db) {
        this.db = db;
    }

    // SiteUser を登録する
    public void insertUser(SiteUser su) {
        String sql = "INSERT INTO SiteUser (password, nickName, adminFlag, deleteFlag, email, created_at, login_at, phone_number) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        db.update(sql,
            su.getPassword(),
            su.getNickName(),
            su.getAdminFlag(),
            su.getDeleteFlag(),
            su.getEmail(),
            su.getCreated_at(),
            su.getLogin_at(),
            su.getPhone_number()
        );
    }

    // SiteUser を取得する（例：id による単一取得）
    public SiteUser getUserById(int id) {
        String sql = "SELECT * FROM SiteUser WHERE id = ?";
        return db.queryForObject(sql, new SiteUserRowMapper(), id);
    }

    // SiteUser 一覧取得
    public List<SiteUser> getAllUsers() {
        String sql = "SELECT * FROM SiteUser";
        return db.query(sql, new SiteUserRowMapper());
    }

    // SiteUser 削除
    public void deleteUserById(int id) {
        String sql = "DELETE FROM SiteUser WHERE id = ?";
        db.update(sql, id);
    }

    // SiteUser の更新
    public void updateUser(int ID,String field,String value) {	    
	    String sql = "UPDATE SiteUser SET " + field + " = ? WHERE id = ?";
	    db.update(sql, value, ID);
	}

    // RowMapper クラス
    private static class SiteUserRowMapper implements RowMapper<SiteUser> {
        @Override
        public SiteUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            SiteUser user = new SiteUser();
            user.setId(rs.getInt("id"));
            user.setPassword(rs.getString("password"));
            user.setNickName(rs.getString("nickName"));
            user.setAdminFlag(rs.getBoolean("adminFlag"));
            user.setDeleteFlag(rs.getBoolean("deleteFlag"));
            user.setEmail(rs.getString("email"));
            user.setCreated_at(rs.getTimestamp("created_at"));
            user.setLogin_at(rs.getTimestamp("login_at"));
            user.setPhone_number(rs.getString("phone_number"));
            return user;
        }
    }
    
    public int count() {
		String sql = "SELECT COUNT(*) FROM (SELECT * FROM SiteUser) AS sub";
		return db.queryForObject(sql, Integer.class);
	}
	
	public String makeSQL(String[] sqls) {
		String sql = "SELECT * FROM SiteUser ";
		
		String contentRQ="";
		String sdRQ="";
		String edRQ="";
		
		if(!sqls[0].equals("")) {
			contentRQ="WHERE content LIKE '%"+sqls[0]+"%' ";
		}
		
		if(!sqls[1].equals("")) {
			if (sqls[0].equals("")) {
				sdRQ="WHERE date >= '"+sqls[1]+"' ";
			} else {
				sdRQ="AND date >= '"+sqls[1]+"' ";
			}
		}
		
		if(!sqls[2].equals("")) {
			if (sqls[0].equals("")&&sqls[1].equals("")) {
				edRQ="WHERE date <= '"+sqls[2]+"' ";
			} else {
				edRQ="AND date <= '"+sqls[2]+"' ";
			}
		}
		
		
		sql = sql+contentRQ+sdRQ+edRQ;
		return sql;
	}
	public SiteUser findUserByNickAndPassword(String nickName, String password) {
	    String sql = "SELECT * FROM SiteUser WHERE nickName = ? AND password = ?";
	    try {
	        return db.queryForObject(sql, new SiteUserRowMapper(), nickName, password);
	    } catch (Exception e) {
	        return null; 
	    }
	}
	

}
