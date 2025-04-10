package com.exercise.area.controller;

import com.exercise.area.service.UserAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user-areas")
public class UserAreaController {

	@Autowired
	private UserAreaService userAreaService;

	@PostMapping("/assign")
    public String assignAreaToUser(@RequestParam Integer userId, @RequestParam Integer areaId) {
        userAreaService.assignAreaToUser(userId, areaId);
        return "redirect:/users/edit/" + userId;
    }

	@GetMapping("/remove")
    public String removeAreaFromUser(@RequestParam Integer userId, @RequestParam Integer areaId) {
        userAreaService.removeAreaFromUser(userId, areaId);
        return "redirect:/users/edit/" + userId;
    }
}