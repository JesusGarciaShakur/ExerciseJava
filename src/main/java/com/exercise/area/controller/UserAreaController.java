package com.exercise.area.controller;

import com.exercise.area.model.Area;
import com.exercise.area.model.User;
import com.exercise.area.model.UserArea;
import com.exercise.area.repository.AreaRepository;
import com.exercise.area.repository.UserAreaRepository;
import com.exercise.area.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user-areas")
public class UserAreaController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private UserAreaRepository userAreaRepository;

	@GetMapping("/list")
	public String listUserAreas(Model model) {
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "user-area/user-areas-list";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Integer id, Model model) {
		UserArea userArea = userAreaRepository.findById(id).orElse(null);
		if (userArea == null)
			return "redirect:/user-areas/list?error=notfound";

		model.addAttribute("userArea", userArea);
		model.addAttribute("areas", areaRepository.findAll());
		return "user-area/edit-user-area";
	}

	@PostMapping("/edit/{id}")
	public String updateUserArea(@PathVariable("id") Integer id, @RequestParam("areaId") Integer newAreaId) {
		UserArea userArea = userAreaRepository.findById(id).orElse(null);
		Area newArea = areaRepository.findById(newAreaId).orElse(null);

		if (userArea != null && newArea != null) {

			Integer userId = userArea.getUser().getUserId();
			boolean yaAsignado = userAreaRepository.findAll().stream()
					.anyMatch(ua -> ua.getUser().getUserId().equals(userId)
							&& ua.getArea().getAreaId().equals(newAreaId) && !ua.getId().equals(id));

			if (yaAsignado) {

				return "redirect:/user-areas/edit/" + id + "?error=duplicado";
			}

			userArea.setArea(newArea);
			userAreaRepository.save(userArea);
		}

		return "redirect:/user-areas/list?updated";
	}

	@PostMapping("/delete/{id}")
	public String deleteUserArea(@PathVariable("id") Integer id) {
		userAreaRepository.deleteById(id);
		return "redirect:/user-areas/list?deleted";
	}

	@GetMapping("/assign")
	public String showAssignForm(Model model) {
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("areas", areaRepository.findAll());
		return "user-area/assign-area";
	}

	@PostMapping("/assign")
	public String assignAreasToUser(@RequestParam("userId") Integer userId,
			@RequestParam("areaIds") List<Integer> areaIds) {

		User user = userRepository.findById(userId).orElse(null);
		if (user == null)
			return "redirect:/user-areas/assign?error=user";

		List<Integer> areasAsignadas = user.getUserAreas().stream().map(ua -> ua.getArea().getAreaId()).toList();

		for (Integer areaId : areaIds) {
			if (!areasAsignadas.contains(areaId)) {
				Area area = areaRepository.findById(areaId).orElse(null);
				if (area != null) {
					UserArea userArea = new UserArea(user, area);
					userAreaRepository.save(userArea);
				}
			}
		}

		return "redirect:/user-areas/list?assigned";
	}

}
