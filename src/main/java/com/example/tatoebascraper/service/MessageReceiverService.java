package com.example.tatoebascraper.service;

import com.example.tatoebascraper.telegram.send.SendMessageResponseDTO;
import com.example.tatoebascraper.telegram.send.text.SendMessageDTO;
import com.example.tatoebascraper.telegram.update.TelegramUpdateDTO;

import java.io.IOException;
import java.text.ParseException;

public interface MessageReceiverService {

    TelegramUpdateDTO getUpdates();

    SendMessageResponseDTO sendMessage(SendMessageDTO sendMessageDTO);

    SendMessageResponseDTO reply(TelegramUpdateDTO telegramUpdateDTO) throws IOException, ParseException;
}
