package com.example.tatoebascraper.repository;

import com.example.tatoebascraper.entity.Chat;

import java.util.List;

public interface SearchParameterCustomRepository {

    List<Chat> getChatListBySearchedParameters( Long price, Long numberOfRooms);

}
