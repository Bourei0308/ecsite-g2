package com.example.demo.userEdit;

import jakarta.servlet.http.HttpSession;

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
	public String mypage(
			Model model,SiteUser su,SiteUserInfo info,HttpSession session) {
		int ID = (su.getID()!=null)?su.getID():1;
		
		su = db.getUserById(ID);
		info = infodb.getById(ID);
		
		model.addAttribute("info", info);
		model.addAttribute("su", su);
		session.setAttribute("info", info);
		session.setAttribute("su", su);
		return "mypage";
	}
	
	@RequestMapping("/mypage/edit")
	public String useredit(
			Model model,SiteUser su,SiteUserInfo info,HttpSession session) {
		su = (SiteUser)session.getAttribute("su");
		info = (SiteUserInfo)session.getAttribute("info");

		return "useredit";
	}
}
