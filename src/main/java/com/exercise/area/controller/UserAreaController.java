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

    // Mostrar formulario de asignación
    @GetMapping("/assign")
    public String showAssignForm(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("areas", areaRepository.findAll());
        return "area/assign-area";
    }

    // Procesar formulario
    @PostMapping("/assign")
    public String assignAreasToUser(@RequestParam("userId") Integer userId,
                                    @RequestParam("areaIds") List<Integer> areaIds) {

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return "redirect:/user-areas/assign?error=user";

        for (Integer areaId : areaIds) {
            Area area = areaRepository.findById(areaId).orElse(null);
            if (area != null) {
                UserArea userArea = new UserArea(user, area);
                userAreaRepository.save(userArea);
            }
        }

        return "redirect:/user-areas/assign?success";
    }
}
