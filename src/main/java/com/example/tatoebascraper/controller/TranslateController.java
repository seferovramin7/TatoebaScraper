package com.example.tatoebascraper.controller;

import com.example.tatoebascraper.dto.ResponseDto;
import com.example.tatoebascraper.service.impl.RestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("translate")
public class TranslateController {
    private final RestService restService;

    @GetMapping
    public ResponseDto translate(@RequestParam String from, @RequestParam String to, @RequestParam String word) throws IOException {
        HashMap translated = restService.findTranslate(from, to, word);
        return ResponseDto.builder()
                .fromLang(translated.keySet().stream().findFirst().get().toString())
                .toLang(translated.values().stream().findFirst().get().toString())
                .build();
    }
}
