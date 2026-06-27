package com.bloom.backend.tracking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cycles")
@RequiredArgsConstructor
public class CycleLogController {

    private final CycleLogService cycleLogService;

    @PostMapping
    public ResponseEntity<CycleLog> createCycleLog(
            Authentication authentication,
            @RequestBody CycleLog cycleLog
    ) {
        String userEmail = authentication.getName();
        CycleLog saved = cycleLogService.createCycleLog(userEmail, cycleLog);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<CycleLog>> getMyCycleLogs(Authentication authentication) {
        String userEmail = authentication.getName();
        List<CycleLog> logs = cycleLogService.getCycleLogsForUser(userEmail);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CycleLog> getCycleLogById(
            Authentication authentication,
            @PathVariable Long id
    ) {
        String userEmail = authentication.getName();
        CycleLog log = cycleLogService.getCycleLogById(userEmail, id);
        return ResponseEntity.ok(log);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CycleLog> updateCycleLog(
            Authentication authentication,
            @PathVariable Long id,
            @RequestBody CycleLog updatedData
    ) {
        String userEmail = authentication.getName();
        CycleLog updated = cycleLogService.updateCycleLog(userEmail, id, updatedData);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCycleLog(
            Authentication authentication,
            @PathVariable Long id
    ) {
        String userEmail = authentication.getName();
        cycleLogService.deleteCycleLog(userEmail, id);
        return ResponseEntity.ok("Cycle log deleted successfully");
    }
}