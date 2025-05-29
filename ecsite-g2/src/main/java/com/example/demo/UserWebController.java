package com.example.demo;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.Dao;
import com.example.demo.user.Check;
import com.example.demo.user.SiteUser;

@Controller
public class UserWebController {

	private final Dao dao;

	@Autowired
	private Check check;

	public UserWebController(Dao dao) {
		this.dao = dao;
	}

	//ログイン画面
	@RequestMapping("/login")
	public String index(SiteUser su, Model model,HttpSession session) {
		su = (SiteUser)session.getAttribute("su");
		if(su!=null) {
			session.removeAttribute("su");
		}

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
	public String confirm(@Validated @ModelAttribute("su") SiteUser su, BindingResult result,
			@RequestParam("confirmPassword") String confirmPassword, Model model) {
		//パスワード一致チェック
		if (!su.getPassword().equals(confirmPassword)) {
			result.rejectValue("password", "error.password", "パスワードが一致しません");
			return "login/signIn";
		}
		// メールアドレス重複チェック
		if (check.isEmailExists(su.getEmail())) {
			result.rejectValue("email", "error.email", "このメールアドレスは既に使用されています");
			return "login/signIn";
		}

		// 電話番号重複チェック
		if (check.isPhoneNumberExists(su.getPhone_number())) {
			result.rejectValue("phone_number", "error.phone_number", "この電話番号は既に使用されています");
			return "login/signIn";
		}
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

	//ログイン機能
	@PostMapping("/login")
	public String login(@ModelAttribute("su") SiteUser su, Model model,HttpSession session) {
		
		su = dao.findUserByPhoneOrEmailAndPassword(su.getPhone_number(), su.getPassword());
		
		if (su != null) {
			model.addAttribute("su", su); // 必要に応じてセッション管理
			session.setAttribute("su", su); // 必要に応じてセッション管理
			return "redirect:/mypage"; // mypage.html に遷移
		} else {
			model.addAttribute("error", "ログイン情報が正しくありません");
			return "index";
		}
	}

}
