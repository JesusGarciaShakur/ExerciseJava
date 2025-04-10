package com.exercise.area.service;

import com.exercise.area.model.LoginRecord;
import com.exercise.area.model.User;
import com.exercise.area.repository.LoginRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginRecordService {

    @Autowired
    private LoginRecordRepository loginRecordRepository;

    public LoginRecord recordLogin(User user) {
        LoginRecord record = new LoginRecord();
        record.setUser(user);
        record.setLoginTime(LocalDateTime.now());
        return loginRecordRepository.save(record);
    }

    public List<LoginRecord> getUserLoginHistory(Integer userId) {
        return loginRecordRepository.findByUserUserIdOrderByLoginTimeDesc(userId);
    }

    public LocalDateTime getLastLoginTime(Integer userId) {
        List<LoginRecord> records = loginRecordRepository.findByUserUserIdOrderByLoginTimeDesc(userId);
        return records.isEmpty() ? null : records.get(0).getLoginTime();
    }
}