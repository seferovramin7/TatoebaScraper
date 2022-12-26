package com.example.tatoebascraper.service.impl;


import com.example.tatoebascraper.configuration.HttpClientConfig;
import com.example.tatoebascraper.configuration.RestTemplateConfig;
import com.example.tatoebascraper.dto.flight.response.FlightResponse;
import com.example.tatoebascraper.util.ParseHTML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service
@ContextConfiguration(classes = {RestTemplateConfig.class, HttpClientConfig.class})
public class RestService {


    private static RestTemplate restTemplate;

    @Autowired
    ParseHTML parseHTML;

    @Value("${tatoeba.url}")
    public String tatoebaUrl;


    public static void main(String[] args) throws IOException {

        String from = "eng";
        String to = "tur";
        String word = "hello";

        String url = "https://tatoeba.org/en/sentences/search?"
                + "from=" + from
                + "&query=" + word
                + "&to=" + to;

        Document doc = Jsoup.connect(url).get();
//        String title = doc.title();
//        System.out.println("title is: " + title);

//        System.out.println(doc);

        String xpath = "html > body.responsive > div#content > div.container > section > md-content.md-whiteframe-1dp" +
                " > div.sentence-and-translations.md-whiteframe-1dp";
        Elements elements = doc.select(xpath);
//        System.out.println(elements);
//        System.out.println(elements.text());
        System.out.println(elements.outerHtml());

    }
}
