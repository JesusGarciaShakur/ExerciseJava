package com.exercise.area.controller;

import com.exercise.area.model.Area;
import com.exercise.area.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/areas")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@GetMapping
	public String listAreas(Model model) {
		model.addAttribute("areas", areaService.getAllAreas());
		return "area/list";
	}

	@GetMapping("/create")
	public String showCreateForm(Model model) {
		model.addAttribute("area", new Area());
		return "area/create";
	}

	@PostMapping("/save")
	public String saveArea(@ModelAttribute Area area) {
		areaService.createArea(area);
		return "redirect:/areas";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Integer id, Model model) {
		Area area = areaService.getAreaById(id).orElse(null);
		if (area == null)
			return "redirect:/areas";
		model.addAttribute("area", area);
		return "area/edit";
	}

	@PostMapping("/update/{id}")
	public String updateArea(@PathVariable Integer id, @ModelAttribute Area area) {
		areaService.updateArea(id, area);
		return "redirect:/areas";
	}

	@GetMapping("/delete/{id}")
	public String deleteArea(@PathVariable Integer id) {
		areaService.deleteArea(id);
		return "redirect:/areas";
	}
}
