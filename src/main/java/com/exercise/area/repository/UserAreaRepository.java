package com.exercise.area.repository;

import com.exercise.area.model.UserArea;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserAreaRepository extends JpaRepository<UserArea, Integer> {
    List<UserArea> findByUserUserId(Integer userId);
}