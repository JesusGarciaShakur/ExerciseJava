package com.exercise.area.service;

import com.exercise.area.model.*;
import com.exercise.area.repository.UserAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserAreaService {

    @Autowired
    private UserAreaRepository userAreaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AreaService areaService;

    @Transactional
    public UserArea assignAreaToUser(Integer userId, Integer areaId) {
        User user = userService.getUserById(userId).orElseThrow();
        Area area = areaService.getAreaById(areaId).orElseThrow();

        UserAreaId userAreaId = new UserAreaId(userId.toString(), areaId);
        UserArea userArea = new UserArea(user, area);
        
        return userAreaRepository.save(userArea);
    }

    public List<UserArea> getUserAreas(Integer userId) {
        return userAreaRepository.findByUserUserId(userId);
    }
}