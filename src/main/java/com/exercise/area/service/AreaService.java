package com.exercise.area.service;

import com.exercise.area.model.Area;
import com.exercise.area.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;
    
    public Area createArea(Area area) {
        return areaRepository.save(area);
    }

    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    public Optional<Area> getAreaById(Integer areaId) {
        return areaRepository.findById(areaId);
    }

    public Area updateArea(Integer id, Area areaDetails) {
        return areaRepository.findById(id).map(area -> {
            area.setAreaName(areaDetails.getAreaName());
            area.setAreaDescription(areaDetails.getAreaDescription());
            return areaRepository.save(area);
        }).orElse(null);
    }

    public void deleteArea(Integer areaId) {
        areaRepository.deleteById(areaId);
    }
}
