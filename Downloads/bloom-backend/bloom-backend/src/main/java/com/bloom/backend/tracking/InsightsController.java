package com.bloom.backend.tracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/insights")
public class InsightsController {

    @Autowired
    private InsightsService insightsService;

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getInsights(@PathVariable Long userId) {
        return ResponseEntity.ok(insightsService.getInsights(userId));
    }
}