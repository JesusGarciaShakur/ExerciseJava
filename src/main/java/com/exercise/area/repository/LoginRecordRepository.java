package com.exercise.area.repository;

import com.exercise.area.model.LoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoginRecordRepository extends JpaRepository<LoginRecord, Long> {
    List<LoginRecord> findByUserUserIdOrderByLoginTimeDesc(Integer userId);
}