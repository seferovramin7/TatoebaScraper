package com.example.tatoebascraper.service.impl;


import com.example.tatoebascraper.entity.SearchParameter;
import com.example.tatoebascraper.repository.TurboMakeRepository;
import com.example.tatoebascraper.repository.TurboModelRepository;
import com.example.tatoebascraper.telegram.send.text.NotificationDTO;
import com.example.tatoebascraper.util.CarTypeMapper;
import com.example.tatoebascraper.util.URLcreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class RequestCreationService {

    @Autowired
    CarTypeMapper carTypeMapper;

    @Autowired
    URLcreator urLcreator;

    @Autowired
    RestService restService;

    @Autowired
    TurboMakeRepository turboMakeRepository;

    @Autowired
    TurboModelRepository turboModelRepository;


    public List<NotificationDTO> createRequest(SearchParameter searchParameter) throws IOException, ParseException {
        return null;
    }
}
