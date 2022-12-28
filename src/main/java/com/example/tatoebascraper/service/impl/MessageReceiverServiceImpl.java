package com.example.tatoebascraper.service.impl;

import com.example.tatoebascraper.service.HttpRequestService;
import com.example.tatoebascraper.service.MessageReceiverService;
import com.example.tatoebascraper.telegram.send.ReplyKeyboardRemoveDTO;
import com.example.tatoebascraper.telegram.send.SendMessageResponseDTO;
import com.example.tatoebascraper.telegram.send.text.SendMessageDTO;
import com.example.tatoebascraper.telegram.update.TelegramResponseDTO;
import com.example.tatoebascraper.telegram.update.TelegramUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageReceiverServiceImpl implements MessageReceiverService {

    private final HttpRequestService httpRequestService;
    @Autowired
    RestService restService;
    @Value("${telegram.api.base-url}")
    private String telegramApiBaseUrl;
    @Value("${telegram.api.token}")
    private String botToken;
    @Value("${telegram.bot.name}")
    private String botName;
    private Long offset = null;
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");


    @Override
    public TelegramUpdateDTO getUpdates() {
        String url = telegramApiBaseUrl + "/bot" + botToken + "/getUpdates";
        if (offset != null)
            url = url + "?offset=" + offset;
        TelegramResponseDTO telegramResponseDTO = httpRequestService.sendGetRequest(url, TelegramResponseDTO.class);
        if (telegramResponseDTO.getResult().size() > 0) {
            if (telegramResponseDTO.getResult().get(0).getMessageDTO() != null) {
                TelegramUpdateDTO telegramUpdateDTO = telegramResponseDTO.getResult().get(0);
                log.info(telegramUpdateDTO.toString());
                telegramUpdateDTO.getMessageDTO().setDate(telegramUpdateDTO.getMessageDTO().getDate() * 1000);
                offset = telegramUpdateDTO.getUpdateId() + 1;
                return telegramUpdateDTO;
            } else {
                offset = telegramResponseDTO.getResult().get(0).getUpdateId() + 1;
                return null;
            }
        } else
            return null;
    }

    @Override
    public SendMessageResponseDTO sendMessage(SendMessageDTO sendMessageDTO) {
        String url = telegramApiBaseUrl + "/bot" + botToken + "/sendMessage";
        SendMessageResponseDTO sendMessageResponseDTO = httpRequestService.sendPostRequest(url, sendMessageDTO, SendMessageResponseDTO.class);
        return sendMessageResponseDTO;
    }

    @Override
    public SendMessageResponseDTO reply(TelegramUpdateDTO telegramUpdateDTO) throws IOException, ParseException {
        // check it is private or group chat
        if (telegramUpdateDTO.getMessageDTO().getChat().getType().equals("group")) {
            String callName = "@" + botName;
            if (telegramUpdateDTO.getMessageDTO().getText().startsWith(callName)) {
                telegramUpdateDTO.getMessageDTO().setText(telegramUpdateDTO.getMessageDTO().getText().substring(callName.length()).trim());
            } else if (telegramUpdateDTO.getMessageDTO().getReplyToMessage() != null) {
                if (!telegramUpdateDTO.getMessageDTO().getReplyToMessage().getFrom().getUsername().equals(botName))
                    return null;
            } else
                return null;
        }

        Long chatId = telegramUpdateDTO.getMessageDTO().getChat().getId();
        String text = telegramUpdateDTO.getMessageDTO().getText().trim();
        Long messageId = telegramUpdateDTO.getMessageDTO().getMessageId();

        HashMap translated = restService.findTranslate("eng", "tur", text, 2);

        sendMessage(getTranslatedMessage(chatId, translated));
        return null;
    }


    private SendMessageDTO getTranslatedMessage(Long chatId, HashMap translated) {
        String text = translated.values().stream().findFirst().get() + "\n" +
                translated.keySet().stream().findFirst().get();

        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setChatId(chatId);
        sendMessageDTO.setText(text);
        sendMessageDTO.setReplyKeyboard(new ReplyKeyboardRemoveDTO(true));
        return sendMessageDTO;
    }
}
