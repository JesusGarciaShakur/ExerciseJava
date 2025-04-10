package com.exercise.area.controller;

import com.exercise.area.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRecordController {

	@Autowired
	private UserService userService;

	@GetMapping("/logged-users")
	public String getLoggedUsers(Model model) {
		model.addAttribute("users", userService.getLoggedInUsersWithLastLogin());
		return "user/logged-users";
	}
}