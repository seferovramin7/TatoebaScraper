package com.example.tatoebascraper.service.impl;

import com.example.tatoebascraper.constant.ChatStage;
import com.example.tatoebascraper.telegram.update.ChatDTO;
import com.example.tatoebascraper.telegram.update.MessageDTO;
import com.example.tatoebascraper.telegram.update.TelegramUpdateDTO;
import com.example.tatoebascraper.telegram.update.UserDTO;
import com.example.tatoebascraper.entity.Chat;
import com.example.tatoebascraper.entity.Message;
import com.example.tatoebascraper.entity.User;
import com.example.tatoebascraper.repository.ChatRepository;
import com.example.tatoebascraper.repository.MessageRepository;
import com.example.tatoebascraper.repository.UserRepository;
import com.example.tatoebascraper.service.ChatDataService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ChatDataServiceImpl implements ChatDataService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatDataServiceImpl(MessageRepository messageRepository,
                               ChatRepository chatRepository,
                               UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveTelegramMessage(TelegramUpdateDTO telegramUpdateDTO) {
        Chat chat = saveChat(telegramUpdateDTO.getMessageDTO().getChat());
        User user = saveUser(telegramUpdateDTO.getMessageDTO().getFrom());
        Message message = saveMessage(telegramUpdateDTO.getMessageDTO(), chat, user);
    }

    @Override
    public Chat saveChat(ChatDTO chatDTO) {
        Long chatId = chatDTO.getId();
        Chat chat = chatRepository.getChatByChatId(chatId);
        if (chat == null) {
            chat = new Chat();
            chat.setChatId(chatDTO.getId());
            chat.setFirstName(chatDTO.getFirstName());
            chat.setLastName(chatDTO.getLastName());
            chat.setTitle(chatDTO.getTitle());
            chat.setType(chatDTO.getType());
            chat.setUsername(chatDTO.getUsername());
            chat.setChatStage(ChatStage.START);
            chat.setBio(chatDTO.getBio());
            chat.setDescription(chatDTO.getDescription());
            chat = chatRepository.save(chat);
        }
        return chat;
    }

    @Override
    public Chat updateChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public User saveUser(UserDTO userDTO) {
        Long userId = userDTO.getId();
        User user = userRepository.getUserById(userId);
        if (user == null) {
            user = new User();
            user.setId(userDTO.getId());
            user.setIsBot(userDTO.getIsBot());
            user.setUsername(userDTO.getUsername());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setLanguageCode(userDTO.getLanguageCode());
            user = userRepository.save(user);
        }
        return user;
    }

    @Override
    public Message saveMessage(MessageDTO messageDTO, Chat chat, User user) {
        Message message = new Message();
        message.setMessageId(messageDTO.getMessageId());
        message.setDate(new Date(messageDTO.getDate()));
        message.setText(messageDTO.getText());
        message.setChat(chat);
        message.setUser(user);
        message = messageRepository.save(message);
        return message;
    }

    @Override
    public Chat getChatByChatId(Long chatId) {
        return chatRepository.getChatByChatId(chatId);
    }
}
