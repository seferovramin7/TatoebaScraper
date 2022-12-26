package com.example.tatoebascraper.repository;

import com.example.tatoebascraper.entity.SearchParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchParameterRepository extends JpaRepository<SearchParameter, Long> {


    List<SearchParameter> getAllByChat_ChatId(Long chatId);

    @Query("select s from SearchParameter s where s.chat.chatId = :chatId")
    SearchParameter getSearchParameterByChatId(@Param(("chatId")) Long chatId);

    List<SearchParameter> findAllByDateAndTimeAndChat_ChatId(String date, String time, Long id);

    SearchParameter findFirstByChat_ChatIdOrderByMessageIdDesc(Long chatId);

    List<SearchParameter> findAllByChat_ChatId(Long chatId);
}
