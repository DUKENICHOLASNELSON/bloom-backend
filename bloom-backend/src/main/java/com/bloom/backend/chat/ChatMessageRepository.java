package com.bloom.backend.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChannelOrderBySentAtAsc(String channel);
    void deleteByChannelAndSentAtBefore(String channel, LocalDateTime cutoff);
}