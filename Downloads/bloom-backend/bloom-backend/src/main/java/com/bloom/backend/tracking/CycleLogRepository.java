package com.bloom.backend.tracking;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CycleLogRepository extends JpaRepository<CycleLog, Long> {

    List<CycleLog> findByUserIdOrderByStartDateDesc(Long userId);
}
