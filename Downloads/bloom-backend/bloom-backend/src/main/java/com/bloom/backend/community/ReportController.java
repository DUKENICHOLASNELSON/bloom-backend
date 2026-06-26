package com.bloom.backend.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/{postId}/user/{userId}")
    public ResponseEntity<Report> reportPost(
            @PathVariable Long postId,
            @PathVariable Long userId,
            @RequestBody Map<String, String> body) {
        String reason = body.get("reason");
        return ResponseEntity.ok(reportService.reportPost(postId, userId, reason));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Report>> getReports(@PathVariable Long postId) {
        return ResponseEntity.ok(reportService.getReportsForPost(postId));
    }
}