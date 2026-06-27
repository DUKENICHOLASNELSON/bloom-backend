package com.bloom.backend.controller;

import com.bloom.backend.model.DischargeLog;
import com.bloom.backend.service.DischargeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discharge")
public class DischargeLogController {

    @Autowired
    private DischargeLogService dischargeLogService;

    @PostMapping("/{userId}")
    public ResponseEntity<DischargeLog> createLog(@PathVariable Long userId, @RequestBody DischargeLog log) {
        return ResponseEntity.ok(dischargeLogService.createLog(userId, log));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<DischargeLog>> getLogsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(dischargeLogService.getLogsByUser(userId));
    }

    @GetMapping("/log/{id}")
    public ResponseEntity<DischargeLog> getLogById(@PathVariable Long id) {
        return dischargeLogService.getLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/log/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        dischargeLogService.deleteLog(id);
        return ResponseEntity.noContent().build();
    }
}