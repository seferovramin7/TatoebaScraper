package com.example.tatoebascraper.util;

import com.example.tatoebascraper.dto.telegram.send.text.NotificationDTO;
import com.example.tatoebascraper.entity.MakeEntity;
import com.example.tatoebascraper.entity.ModelEntity;
import com.example.tatoebascraper.entity.SpecificVehicleSearchParameter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ParseHTML {

    @Autowired
    DBactions dBactions;

    NotificationDTO notificationDTO;

    @Value("${cars.from.minutes}")
    private int minutes;


    public SpecificVehicleSearchParameter parseSpecificCarHTML(String rawHTML) throws ParseException {
        try {
            String carNameString = "";
            Document doc = Jsoup.parse(rawHTML);

            Elements carLot = doc.getElementsByClass("product-statistics");
            String carLotString = carLot.first().html();
            carLotString = carLotString.split(":")[3].trim().replaceAll("</p>", "");
            long carLotLong = Long.parseLong(carLotString);

            Elements carName = doc.getElementsByClass("product-name");
            carNameString += carName.first().html();
            carNameString = carNameString.replaceAll("<span class=\"nobr\">", "").replaceAll("</span>", "");

            String carPriceString = "";
            Elements carPrice = doc.getElementsByClass("product-price");
            carPriceString += carPrice.first().html();
            carPriceString = carPriceString.replaceAll("<span>", "").replaceAll("</span>", "");

            SpecificVehicleSearchParameter specificVehicleSearchParameter = SpecificVehicleSearchParameter.builder()
                    .lotId(carLotLong)
                    .generalInfo(carNameString)
                    .price(carPriceString)
                    .build();
            return specificVehicleSearchParameter;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void parseMakeAndModel(String rawHTML) {
        Document doc = Jsoup.parse(rawHTML);
        Elements make = doc.getElementsByClass("select optional js-search-select-make");
        String allMake = make.first().html();

        Document document = Jsoup.parse(allMake);
        Elements options = document.select("option");

        for (Element element : options) {
            Elements models = doc.getElementsByClass("js-search-select-model");
            String allModels = models.first().html();
            String modelValue = element.attr("value");
            String markaName = element.html();

            if (!modelValue.equals("")) {
                MakeEntity makeEntity = MakeEntity.builder().make(markaName).makeId(Integer.parseInt(modelValue)).build();
                dBactions.updateMakeTable(makeEntity);
            }
            Document modelDoc = Jsoup.parse(allModels);
            Elements modelOptions = modelDoc.select("option");
            for (Element modelElement : modelOptions) {
                if (modelElement.attr("class").equals(modelValue)) {
                    String model = modelElement.html();
                    Integer modelInt = 0;
                    if (!modelValue.equals("")) {
                        try {
                            modelInt = Integer.valueOf(modelElement.attr("value"));
                        } catch (NumberFormatException e) {
                        }
                        ModelEntity modelEntity = ModelEntity.builder().model(model).makeId(Integer.parseInt(modelValue)).modelId(Integer.valueOf(modelInt)).build();
                        dBactions.updateModelTable(modelEntity);
                    }
                }
            }
        }
    }
}
