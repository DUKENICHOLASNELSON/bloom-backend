package com.bloom.backend.tracking;

import com.bloom.backend.user.User;
import com.bloom.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CycleLogService {

    private final CycleLogRepository cycleLogRepository;
    private final UserRepository userRepository;

    public CycleLog createCycleLog(String userEmail, CycleLog cycleLog) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        cycleLog.setUser(user);
        return cycleLogRepository.save(cycleLog);
    }

    public List<CycleLog> getCycleLogsForUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cycleLogRepository.findByUserIdOrderByStartDateDesc(user.getId());
    }

    public CycleLog getCycleLogById(String userEmail, Long cycleLogId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CycleLog cycleLog = cycleLogRepository.findById(cycleLogId)
                .orElseThrow(() -> new RuntimeException("Cycle log not found"));

        if (!cycleLog.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to access this cycle log");
        }

        return cycleLog;
    }

    public CycleLog updateCycleLog(String userEmail, Long cycleLogId, CycleLog updatedData) {
        CycleLog existing = getCycleLogById(userEmail, cycleLogId);

        existing.setStartDate(updatedData.getStartDate());
        existing.setEndDate(updatedData.getEndDate());
        existing.setCycleLength(updatedData.getCycleLength());
        existing.setPeriodLength(updatedData.getPeriodLength());
        existing.setSymptoms(updatedData.getSymptoms());
        existing.setNotes(updatedData.getNotes());

        return cycleLogRepository.save(existing);
    }

    public void deleteCycleLog(String userEmail, Long cycleLogId) {
        CycleLog existing = getCycleLogById(userEmail, cycleLogId);
        cycleLogRepository.delete(existing);
    }
}