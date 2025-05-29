package com.example.demo.userEdit;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.Dao;
import com.example.demo.user.AddressDao;
import com.example.demo.user.SiteUser;
import com.example.demo.user.SiteUserAddress;
import com.example.demo.user.SiteUserInfo;

@Controller
public class UserEditController {
	private final UserInfoDao infodb;
	private final Dao db;
	private final AddressDao addressdb;

	public UserEditController(UserInfoDao infodb, Dao db,AddressDao addressdb) {
		this.db = db;
		this.infodb = infodb;
		this.addressdb = addressdb;
	}

	@RequestMapping("/mypage")
	public String mypage(
			Model model, SiteUser su, SiteUserInfo info, HttpSession session) {
		su = (SiteUser) session.getAttribute("su");
		if (su == null) {
			return "redirect:/login";
		}
		int ID = su.getID();
		info = (infodb.getById(ID) != null) ? infodb.getById(ID) : new SiteUserInfo();

		model.addAttribute("info", info);
		model.addAttribute("su", su);
		session.setAttribute("info", info);
		session.setAttribute("su", su);
		model.addAttribute("isAdmin", su.getAdminFlag() == true); // 添加此行
		return "mypage";
	}

	@RequestMapping("/mypage/edit")
	public String useredit(
			Model model, SiteUser su, SiteUserInfo info, HttpSession session) {
		su = (SiteUser) session.getAttribute("su");
		info = (SiteUserInfo) session.getAttribute("info");

		if (su == null) {
			return "redirect:/login";
		}

		// 放入 model
		model.addAttribute("su", su);
		model.addAttribute("info", info);
		model.addAttribute("isAdmin", su.getAdminFlag() == true);

		return "useredit";
	}

	@PostMapping("/mypage/update")
	public String update(
			@RequestParam("nickName") String nickName,
			@RequestParam("email") String email,
			@RequestParam("phone_number") String phone_number,

			@RequestParam("postNumber1") String postNumber1,
			@RequestParam("postNumber2") String postNumber2,

			@RequestParam("creditNumber1") String creditNumber1,
			@RequestParam("creditNumber2") String creditNumber2,
			@RequestParam("creditNumber3") String creditNumber3,
			@RequestParam("creditNumber4") String creditNumber4,

			@ModelAttribute SiteUserInfo info,
			HttpSession session) {

		// 假设 session 中保存了当前登录用户
		SiteUser su = (SiteUser) session.getAttribute("su");
		if (su == null) {
			return "redirect:/login"; // 未登录跳转登录页
		}
		// 更新 SiteUser
		db.updateUser(su.getID(), "nickName", nickName);
		db.updateUser(su.getID(), "phone_number", phone_number);
		db.updateUser(su.getID(), "email", email);

		// 设定 ID 给 info
		info.setID(su.getID());
		info.setPostNumber(Integer.parseInt(postNumber1 + postNumber2));
		info.setCreditNumber(creditNumber1 + creditNumber2 + creditNumber3 + creditNumber4);

		// 保存 SiteUserInfo
		infodb.update(info);

		return "redirect:/mypage"; // 更新后跳转个人主页
	}

	@RequestMapping("/mypage/addAddress")
	public String addAddress(Model model, SiteUser su, SiteUserInfo info, SiteUserAddress address, HttpSession session) {
		return "mypage/addAddress";
	}

	@RequestMapping("/mypage/editAddress")
	public String editAddress(Model model, SiteUser su, SiteUserInfo info, SiteUserAddress address, HttpSession session) {

		su = (SiteUser) session.getAttribute("su");
		info = (SiteUserInfo) session.getAttribute("info");

		if (su == null) {
			return "redirect:/login";
		}

		// 放入 model
		model.addAttribute("su", su);
		model.addAttribute("info", info);
		model.addAttribute("isAdmin", su.getAdminFlag() == true);
		return "mypage/editAddress";
	}
}
