package com.bloom.backend.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{channel}")
    public void sendMessage(@DestinationVariable String channel, Map<String, String> payload) {
        String content = payload.get("content");
        ChatMessage saved = chatService.saveMessage(content, channel);
        messagingTemplate.convertAndSend("/topic/chat/" + channel, saved);
    }

    @GetMapping("/api/chat/{channel}/history")
    public ResponseEntity<List<ChatMessage>> getHistory(@PathVariable String channel) {
        return ResponseEntity.ok(chatService.getMessagesByChannel(channel));
    }
}