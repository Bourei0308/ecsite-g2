package com.example.demo.userEdit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.Dao;
import com.example.demo.user.SiteUser;
import com.example.demo.user.SiteUserInfo;


@Controller
public class UserEditController {
	private final UserInfoDao infodb;
	private final Dao db;
	
	public UserEditController (UserInfoDao infodb,Dao db) {
		this.db = db;
		this.infodb = infodb;
	}
	
	@RequestMapping("/mypage")
	public String data(
			Model model,SiteUser su,SiteUserInfo info) {
		int ID = su.getID();
		if(info ==null) {
			info = infodb.getById(ID);
		}
		return "mypage";
	}
}
