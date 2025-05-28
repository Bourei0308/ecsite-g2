package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Check {

	@Autowired
	private JdbcTemplate db;

	// メールアドレス重複チェック
	public boolean isEmailExists(String email) {
		String sql = "SELECT COUNT(*) FROM SiteUser WHERE email = ?";
		Integer count = db.queryForObject(sql, Integer.class, email);
		return count != null && count > 0;
	}

	// 電話番号重複チェック
	public boolean isPhoneNumberExists(String phoneNumber) {
		String sql = "SELECT COUNT(*) FROM SiteUser WHERE phone_number = ?";
		Integer count = db.queryForObject(sql, Integer.class, phoneNumber);
		return count != null && count > 0;
	}
}
