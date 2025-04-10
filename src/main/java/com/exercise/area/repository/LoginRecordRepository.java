package com.exercise.area.repository;

import com.exercise.area.model.LoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LoginRecordRepository extends JpaRepository<LoginRecord, Long> {
    @Query("SELECT l FROM LoginRecord l WHERE l.user.userId = :userId ORDER BY l.loginTime DESC")
    List<LoginRecord> findRecentLoginsByUser(@Param("userId") String userId);
    
    @Query("SELECT l FROM LoginRecord l WHERE l.user.userId IN (SELECT DISTINCT l2.user.userId FROM LoginRecord l2) ORDER BY l.loginTime DESC")
    List<LoginRecord> findLatestLoginsForAllUsers();
}
