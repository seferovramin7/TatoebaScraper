package com.example.tatoebascraper.service.impl;

import com.example.tatoebascraper.constant.ChatStage;
import com.example.tatoebascraper.entity.Chat;
import com.example.tatoebascraper.entity.SearchParameter;
import com.example.tatoebascraper.repository.SpecificVehicleRepository;
import com.example.tatoebascraper.repository.TurboMakeRepository;
import com.example.tatoebascraper.repository.TurboModelRepository;
import com.example.tatoebascraper.service.*;
import com.example.tatoebascraper.telegram.send.KeyboardButtonDTO;
import com.example.tatoebascraper.telegram.send.ReplyKeyboardRemoveDTO;
import com.example.tatoebascraper.telegram.send.SendMessageResponseDTO;
import com.example.tatoebascraper.telegram.send.text.SendMessageDTO;
import com.example.tatoebascraper.telegram.update.TelegramResponseDTO;
import com.example.tatoebascraper.telegram.update.TelegramUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageReceiverServiceImpl implements MessageReceiverService {

    private final HttpRequestService httpRequestService;
    private final ChatDataService chatDataService;
    private final MessageProvider messageProvider;
    private final MakeService makeService;
    private final ModelService modelService;
    private final SearchParameterService searchParameterService;
    private final RequestCreationService requestCreationService;
    private final SpecificVehicleRepository specificVehicleRepository;
    private final TurboMakeRepository makeRepository;
    private final TurboModelRepository modelRepository;
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
                chatDataService.saveTelegramMessage(telegramUpdateDTO);
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
    public SendMessageResponseDTO reply(TelegramUpdateDTO telegramUpdateDTO) throws IOException, ParseException, JSONException {
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
        Chat chat = chatDataService.getChatByChatId(chatId);


        if (chat.getChatStage() == ChatStage.START) {
            return sendMessage(getStartMessage(chatId));
        }
        return null;
    }


    private SendMessageDTO getStartMessage(Long chatId) {
        String text = "Nümunə cümlə tapmaq istədiyiniz sözü daxil edin";
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setChatId(chatId);
        sendMessageDTO.setText(text);
        sendMessageDTO.setReplyKeyboard(new ReplyKeyboardRemoveDTO(true));
        return sendMessageDTO;
    }


    private SendMessageDTO getReadyMessage(Long chatId, SearchParameter searchParameter) {
        String text = "Daxil olunan parametrlər əsasında axtarış başlandı :" + " \n"
                + "Çıxış nöqtəsi : " + searchParameter.getFromWhereRaw() + " \n"
                + "İstiqamət : " + searchParameter.getToWhereRaw() + " \n"
                + "Tarix : " + searchParameter.getDate() + " \n"
                + "Saat : " + (searchParameter.getTime().equals("0") ? "Günün istənilən saatı" : searchParameter.getTime()) + " \n";

        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setChatId(chatId);
        sendMessageDTO.setText(text);
        sendMessageDTO.setReplyKeyboard(new ReplyKeyboardRemoveDTO(true));
        return sendMessageDTO;
    }

    private SendMessageDTO getDeleteMessage(Long chatId) {
        List<SearchParameter> searchParameterList = searchParameterService.getSearchParameter(chatId);
        KeyboardButtonDTO[][] buttons = new KeyboardButtonDTO[searchParameterList.size()][];
        for (int i = 0; i < searchParameterList.size(); i++) {
            buttons[i] = new KeyboardButtonDTO[1];
            for (int j = 0; j < 1; j++) {
                buttons[i][j] = new KeyboardButtonDTO(
                        searchParameterList.get(i + j).getFromWhereRaw()
                                + "\n > " +
                                searchParameterList.get(i + j).getToWhereRaw()
                                + "\n"
                                + searchParameterList.get(i + j).getDate() + ";"
                                + "\n"
                                + searchParameterList.get(i + j).getTime()

                );
            }
        }
        return null;
    }
}
