package com.exercise.area.controller;

import com.exercise.area.model.User;
import com.exercise.area.service.LoginRecordService;
import com.exercise.area.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginRecordController {

	@Autowired
	private UserService userService;

	@Autowired
	private LoginRecordService loginRecordService;

	
	@GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login";
    }
	
	@GetMapping("/login")
	public String showLoginForm(@RequestParam(required = false) String error,
			@RequestParam(required = false) String logout, Model model) {
		if (error != null) {
			model.addAttribute("error", "Credenciales incorrectas");
		}
		if (logout != null) {
			model.addAttribute("message", "Has cerrado sesión correctamente");
		}
		return "home/login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
		User user = userService.findByEmailAndPassword(email, password);
		if (user != null) {
			session.setAttribute("user", user);
			loginRecordService.recordLogin(user);
			return "redirect:/users";
		}
		return "redirect:/login?error";
	}

	@GetMapping("/logged-users")
	public String getLoggedUsers(Model model) {
		model.addAttribute("users", userService.getLoggedInUsersWithLastLogin());
		return "user/logged-users";
	}
}