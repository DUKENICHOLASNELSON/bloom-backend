package com.bloom.backend.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    private static final String[] ADJECTIVES = {
        "Purple", "Golden", "Silver", "Crimson", "Azure",
        "Jade", "Coral", "Amber", "Violet", "Scarlet"
    };

    private static final String[] NOUNS = {
        "Orchid", "Rose", "Lily", "Daisy", "Iris",
        "Lotus", "Tulip", "Dahlia", "Jasmine", "Poppy"
    };

    private String generatePseudonym() {
        Random random = new Random();
        String adjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
        String noun = NOUNS[random.nextInt(NOUNS.length)];
        int number = random.nextInt(90) + 10;
        return adjective + noun + number;
    }

    public ChatMessage saveMessage(String content, String channel) {
        ChatMessage message = new ChatMessage();
        message.setPseudonym(generatePseudonym());
        message.setContent(content);
        message.setChannel(channel);
        message.setSentAt(LocalDateTime.now());
        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getMessagesByChannel(String channel) {
        return chatMessageRepository.findByChannelOrderBySentAtAsc(channel);
    }

    @Transactional
    public void deleteOldMessages(String channel) {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(30);
        chatMessageRepository.deleteByChannelAndSentAtBefore(channel, cutoff);
    }
}
