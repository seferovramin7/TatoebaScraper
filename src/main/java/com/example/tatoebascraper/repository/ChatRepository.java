package com.example.tatoebascraper.repository;

import com.example.tatoebascraper.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    Chat getChatByChatId(Long chatId);

}
