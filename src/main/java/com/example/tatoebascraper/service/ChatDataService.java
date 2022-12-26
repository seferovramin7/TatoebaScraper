package com.example.tatoebascraper.service;

import com.example.tatoebascraper.telegram.update.ChatDTO;
import com.example.tatoebascraper.telegram.update.MessageDTO;
import com.example.tatoebascraper.telegram.update.TelegramUpdateDTO;
import com.example.tatoebascraper.telegram.update.UserDTO;
import com.example.tatoebascraper.entity.Chat;
import com.example.tatoebascraper.entity.Message;
import com.example.tatoebascraper.entity.User;

public interface ChatDataService {

    void saveTelegramMessage(TelegramUpdateDTO telegramUpdateDTO);

    Chat saveChat(ChatDTO chatDTO);

    Chat updateChat(Chat chat);

    User saveUser(UserDTO userDTO);

    Message saveMessage(MessageDTO messageDTO, Chat chat, User user);

    Chat getChatByChatId(Long chatId);

}
