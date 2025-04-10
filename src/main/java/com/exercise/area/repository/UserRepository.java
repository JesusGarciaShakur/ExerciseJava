package com.exercise.area.repository;

import com.exercise.area.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    
    @Query("SELECT u FROM User u JOIN u.userAreas ua WHERE ua.area.areaId = :areaId")
    List<User> findByAreaId(@Param("areaId") Integer areaId);
}