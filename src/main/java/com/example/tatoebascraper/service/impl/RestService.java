package com.example.tatoebascraper.service.impl;


import com.example.tatoebascraper.dto.tatoeba.TatoebaData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;


@Service
public class RestService {
    @Value("${tatoeba.url}")
    public String tatoebaUrl;


    public HashMap findTranslate(String from, String to, String word) throws IOException {
        HashMap<String, String> response = new HashMap<String, String>();
        String xpath = "html > body.responsive > div#content > div.container > section > md-content.md-whiteframe-1dp" +
                " > div.sentence-and-translations.md-whiteframe-1dp";
        Elements elements = null;
        int page = (int) Math.floor((Math.random() * 3) + 1);
        int wordOfPage = (int) Math.floor((Math.random() * 10));
        HashMap<String, String> response1 = getStringStringHashMap(from, to, word, response, xpath, page, wordOfPage);
        if (response1 != null) return response1;
        response.put("Translation not found", "");
        return response;
    }

    private HashMap<String, String> getStringStringHashMap(String from, String to, String word, HashMap<String, String> response, String xpath, int page, int wordOfPage) throws IOException {
        try {
            HashMap<String, String> response1 = getMap(from, to, word, response, xpath, page, wordOfPage);
            if (response1 != null) return response1;
        } catch (Exception e) {
            wordOfPage = (int) Math.floor((Math.random() * 3) + 1);
            page = (int) Math.floor((Math.random() * 10));
            HashMap<String, String> response1 = getStringStringHashMap(from, to, word, response, xpath, page, wordOfPage);
            if (response1 != null) return response1;
            response.put("Translation not found", "");
            return response;
        }
        return null;
    }

    private HashMap<String, String> getMap(String from, String to, String word, HashMap<String, String> response, String xpath, int page, int wordOfPage) throws IOException {
        Elements elements;
        String url = tatoebaUrl + "from=" + from + "&query=" + word + "&to=" + to + "&page=" + page;
        Document doc = Jsoup.connect(url).get();
        elements = doc.select(xpath);
        Element element = elements.get(wordOfPage);
        boolean b = element.hasAttr("ng-init");
        if (b) {
            String attr = element.attr("ng-init");
            String[] s1 = attr.split("vm.init\\(\\[],");
            String[] s2 = s1[1].split(", \\[\\{");
            String result = s2[0];

            TatoebaData student = new ObjectMapper().readValue(result, TatoebaData.class);
            response.put(student.getText(), student.getTranslations().get(0).get(0).text);
            return response;
        }
        return null;
    }
}
