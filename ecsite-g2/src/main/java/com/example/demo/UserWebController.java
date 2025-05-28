package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.Dao;
import com.example.demo.user.SiteUser;

@Controller
public class UserWebController {

	private final Dao dao;

	public UserWebController(Dao dao) {
		this.dao = dao;
	}

	//ログイン画面
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("su", new SiteUser());
		return "index";
	}

	//ユーザー登録入力画面
	@RequestMapping("/signIn")
	public String signIn(Model model) {
		model.addAttribute("su", new SiteUser());
		return "login/signIn";
	}

	//確認画面
	@RequestMapping("/confirm")
	public String confirm(@Validated @ModelAttribute("su") SiteUser su, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "login/signIn";
		}
		model.addAttribute("su", su);
		return "login/confirm";
	}

	//完了画面
	@RequestMapping("/complete")
	public String complete(Model model, SiteUser su) {
		dao.insertUser(su);
		return "login/complete";
	}

	//サインイン機能
	@GetMapping("/signInUser")
	public String signInUser(Model model) {
		model.addAttribute("su", new SiteUser());
		return "index";
	}

}
