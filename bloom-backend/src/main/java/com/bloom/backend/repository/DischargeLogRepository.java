package com.bloom.backend.repository;

import com.bloom.backend.model.DischargeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DischargeLogRepository extends JpaRepository<DischargeLog, Long> {
    List<DischargeLog> findByUserId(Long userId);
}