package com.example.tatoebascraper.service;

import com.example.tatoebascraper.entity.SearchParameter;
import com.example.tatoebascraper.entity.SpecificVehicleSearchParameter;

import java.util.List;

public interface SearchParameterService {

    SearchParameter getSearchParameterByMaxMessageId(Long chatId);

    List<SearchParameter> getSearchParameter(Long chatId);

    List<SpecificVehicleSearchParameter> getSpecificSearchParameter(Long chatId);


    SearchParameter saveSearchParameter(SearchParameter searchParameter);

    SearchParameter updateSearchParameter(SearchParameter searchParameter);

    void deleteSearchParameterByDateAndTime(String date, String time, Long chatId);
}
