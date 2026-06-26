package com.bloom.backend.service;

import com.bloom.backend.model.DischargeLog;
import com.bloom.backend.user.User;
import com.bloom.backend.user.UserRepository;
import com.bloom.backend.repository.DischargeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DischargeLogService {

    @Autowired
    private DischargeLogRepository dischargeLogRepository;

    @Autowired
    private UserRepository userRepository;

    public DischargeLog createLog(Long userId, DischargeLog log) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.setUser(user);
        return dischargeLogRepository.save(log);
    }

    public List<DischargeLog> getLogsByUser(Long userId) {
        return dischargeLogRepository.findByUserId(userId);
    }

    public Optional<DischargeLog> getLogById(Long id) {
        return dischargeLogRepository.findById(id);
    }

    public void deleteLog(Long id) {
        dischargeLogRepository.deleteById(id);
    }
}