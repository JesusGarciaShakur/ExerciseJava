package com.exercise.area.repository;

import com.exercise.area.model.UserArea;
import com.exercise.area.model.UserAreaId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserAreaRepository extends JpaRepository<UserArea, UserAreaId> {
    List<UserArea> findByUserUserId(Integer userId);
}