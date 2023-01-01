package com.example.tatoebascraper.service.impl;

import com.example.tatoebascraper.dto.tatoeba.TatoebaData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Elements elements;
        String url = "https://tatoeba.org/en/sentences/search?" + "from=" +
                "tur" + "&query=" + "girişim" + "&to=" + "rus" + "&page=" + 1;

        String xpath = "html > body.responsive > div#content > div.container > section > md-content.md-whiteframe-1dp" +
                " > div.sentence-and-translations.md-whiteframe-1dp";

        Document doc = Jsoup.connect(url).get();
        elements = doc.select(xpath);
        System.out.println(doc.outerHtml());
        Element element = elements.get(9);
        boolean b = element.hasAttr("ng-init");
        if (b) {
            String attr = element.attr("ng-init");
            String[] s1 = attr.split("vm.init\\(\\[],");
            String[] s2 = s1[1].split(", \\[\\{");
            String result = s2[0];

            TatoebaData student = new ObjectMapper().readValue(result, TatoebaData.class);
            String a =  student.getText();
            System.out.println(a);
            System.out.println(student);
            String binsry;
            try {
                binsry = student.getTranslations().get(0).get(0).text;
            } catch (Exception e){
                  binsry = student.getTranslations().get(1).get(0).text;
            }

            System.out.println(a + " " + binsry);

    }
}}
