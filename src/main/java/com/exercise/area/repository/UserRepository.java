package com.exercise.area.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exercise.area.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}