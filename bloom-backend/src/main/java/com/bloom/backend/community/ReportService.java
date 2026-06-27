package com.bloom.backend.community;

import com.bloom.backend.user.User;
import com.bloom.backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Report reportPost(Long postId, Long userId, String reason) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Report report = new Report();
        report.setPost(post);
        report.setReportedBy(user);
        report.setReason(reason);
        return reportRepository.save(report);
    }

    public List<Report> getReportsForPost(Long postId) {
        return reportRepository.findByPostId(postId);
    }
}