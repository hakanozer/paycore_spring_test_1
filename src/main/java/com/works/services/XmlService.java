package com.works.services;

import org.jsoup.*;

import com.works.props.Currency;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class XmlService {

    public List<Currency> xml() {
        List<Currency> ls = new ArrayList<>();
        try {
            String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
            String data = Jsoup.connect(url).timeout(30000).ignoreContentType(true).get().toString();
            Document doc = Jsoup.parse(data, Parser.xmlParser());
            Elements elements = doc.getElementsByTag("Currency");
            for (Element item : elements) {
                String isim = item.getElementsByTag("Isim").text();
                String banknoteBuying = item.getElementsByTag("BanknoteBuying").text();
                String banknoteSelling = item.getElementsByTag("BanknoteSelling").text();
                Currency c = new Currency(isim, banknoteBuying, banknoteSelling);
                ls.add(c);
            }
        }catch (Exception ex) {
            System.err.println("Error : " + ex);
        }
        return ls;
    }

}
