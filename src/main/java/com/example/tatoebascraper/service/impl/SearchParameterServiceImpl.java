package com.example.tatoebascraper.service.impl;

import com.example.tatoebascraper.entity.SearchParameter;
import com.example.tatoebascraper.entity.SpecificVehicleSearchParameter;
import com.example.tatoebascraper.repository.SearchParameterRepository;
import com.example.tatoebascraper.repository.SpecificVehicleRepository;
import com.example.tatoebascraper.service.SearchParameterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchParameterServiceImpl implements SearchParameterService {

    private final SearchParameterRepository searchParameterRepository;
    private final SpecificVehicleRepository specificVehicleRepository;

    public SearchParameterServiceImpl(SearchParameterRepository searchParameterRepository, SpecificVehicleRepository specificVehicleRepository) {
        this.searchParameterRepository = searchParameterRepository;
        this.specificVehicleRepository = specificVehicleRepository;
    }

    @Override
    public SearchParameter getSearchParameterByMaxMessageId(Long chatId) {
        return searchParameterRepository.findFirstByChat_ChatIdOrderByMessageIdDesc(chatId);
    }

    @Override
    public List<SearchParameter> getSearchParameter(Long chatId) {
        return searchParameterRepository.findAllByChat_ChatId(chatId);
    }

    @Override
    public List<SpecificVehicleSearchParameter> getSpecificSearchParameter(Long chatId) {
        return specificVehicleRepository.findAllByChat_ChatId(chatId);
    }

    @Override
    public SearchParameter saveSearchParameter(SearchParameter searchParameter) {
        return searchParameterRepository.save(searchParameter);
    }

    @Override
    public SearchParameter updateSearchParameter(SearchParameter searchParameter) {
        return searchParameterRepository.save(searchParameter);
    }


    public void deleteSearchParameterByDateAndTime(String date,
                                                   String time,
                                                   Long chatId) {
        List<SearchParameter> all = searchParameterRepository.findAllByDateAndTimeAndChat_ChatId(
                date, time, chatId);
        if (all.size() > 0)
            searchParameterRepository.deleteAll(all);
    }
}
