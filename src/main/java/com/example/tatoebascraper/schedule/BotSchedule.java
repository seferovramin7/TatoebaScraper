package com.example.tatoebascraper.schedule;

import com.example.tatoebascraper.service.MessageReceiverService;
import com.example.tatoebascraper.telegram.update.TelegramUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;

@Service
@EnableAsync
@Slf4j
@RequiredArgsConstructor
public class BotSchedule {

    private final MessageReceiverService messageReceiverService;

    @Scheduled(fixedRateString = "500")
    public void getTelegramUpdates() throws   IOException, ParseException {
        TelegramUpdateDTO telegramUpdateDTO = messageReceiverService.getUpdates();
        if (telegramUpdateDTO != null) {
            log.info(telegramUpdateDTO.toString());
            messageReceiverService.reply(telegramUpdateDTO);
        }
    }
}
