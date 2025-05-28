package com.example.demo.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Chat;

@Repository
public class Dao {
	private final JdbcTemplate db;
	public Dao(JdbcTemplate db) {
		this.db=db;
	}
	
	public void insert(Chat chat) {
		String sql = "INSERT INTO chat(name,content) VALUES(?,?)";
		db.update(sql,chat.getName(),chat.getContent());
	}
	
	public void delete(int ID) {
		String sql = "DELETE FROM chat WHERE ID = ?";
		System.out.println(sql);
		db.update(sql,ID);
	}
	
	public List<Chat> select(String[] sqls) {
		String sql = makeSQL(sqls);
		RowMapper<Chat> mapper = (rs,a) -> new Chat(
			rs.getInt("ID"),
			rs.getString("name"),
			rs.getString("content").replace("\n", "<br>"),
			rs.getString("date")
		);
		
		int size = Integer.parseInt(sqls[3]);
		int page = Integer.parseInt(sqls[4]);
		
		int offset = size*(page-1);
		
		sql +="LIMIT "+ size +" OFFSET "+offset;
		
		System.out.println(sql);
		
		return db.query(sql,mapper);
	}
	
	public void update(int ID,String field,String value) {	    
	    String sql = "UPDATE chat SET " + field + " = ? WHERE id = ?";
	    db.update(sql, value, ID);
	}
	
	public Chat get(int ID) {
		String sql = "SELECT * FROM chat WHERE ID = ?";
		RowMapper<Chat> mapper = (rs,a) -> new Chat(
				rs.getInt("ID"),
				rs.getString("name"),
				rs.getString("content"),
				rs.getString("date")
			);
		return db.queryForObject(sql, mapper, ID);
	}
	
	public int count(String[] sqls) {
		String sql = "SELECT COUNT(*) FROM (" + makeSQL(sqls) + ") AS sub";
		return db.queryForObject(sql, Integer.class);
	}
	
	public String makeSQL(String[] sqls) {
		String sql = "SELECT * FROM chat ";
		
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
}
