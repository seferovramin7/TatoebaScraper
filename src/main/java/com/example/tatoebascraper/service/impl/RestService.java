package com.example.tatoebascraper.service.impl;


import com.example.tatoebascraper.configuration.HttpClientConfig;
import com.example.tatoebascraper.configuration.RestTemplateConfig;
import com.example.tatoebascraper.dto.tatoeba.TatoebaData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;


@Service
public class RestService {


    private static RestTemplate restTemplate;

    @Value("${tatoeba.url}")
    public String tatoebaUrl;


    public HashMap findTranslate(String from, String to, String word, int page) throws IOException {
        HashMap<String, String> response = new HashMap<String, String>();
        String xpath = "html > body.responsive > div#content > div.container > section > md-content.md-whiteframe-1dp" +
                " > div.sentence-and-translations.md-whiteframe-1dp";
        Elements elements = null;

        for (int i = 0; i < 10; i++) {
            String url = tatoebaUrl + "from=" + from + "&query=" + word + "&to=" + to + "&page=" + (page - i);
            System.out.println(url);
            Document doc = Jsoup.connect(url).get();
            elements = doc.select(xpath);
            boolean b = elements.hasAttr("ng-init");
            System.out.println("ng-init " + b);
            if (b) {
                String attr = elements.attr("ng-init");
                System.out.println(attr);
                String[] s1 = attr.split("vm.init\\(\\[],");
                String[] s2 = s1[1].split(", \\[\\{");
                String result = s2[0];

                TatoebaData student = new ObjectMapper().readValue(result, TatoebaData.class);
                System.out.println(student.toString());
                response.put(student.getText(), student.getTranslations().get(0).get(0).text);
                return response;
            }
        }


        response.put("Translation not found", "");
        return response;
    }
}
