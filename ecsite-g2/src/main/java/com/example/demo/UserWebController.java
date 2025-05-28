package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.Dao;

@Controller
public class UserWebController {

	private final Dao dao;

	public UserWebController(Dao dao) {
		this.dao = dao;
	}

	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping("/signIn")
	public String signIn(Model model) {
		return "login/signIn";
	}

	@RequestMapping("/confirm")
	public String confirm(Model model) {
		return "login/confirm";
	}

	@RequestMapping("/complete")
	public String complete(Model model) {
		return "login/complete";
	}

}
