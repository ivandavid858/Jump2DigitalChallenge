package com.jump2digital.jump2digitalchallenge.utils;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class ImportData {

    public List<Object> getData() {
        String url = "https://challenges-asset-files.s3.us-east-2.amazonaws.com/jobMadrid/companies.json";
        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(url, String.class);
        JsonParser springParser = JsonParserFactory.getJsonParser();
        List<Object> list = springParser.parseList(resp);

        return list;
    }

    public void printData(List<Object> list) {
        for (Object o : list) {
            if (o instanceof Map) {
                Map<String, Object> map = (Map< String, Object >) o;
                System.out.println("Items found: " + map.size());
                int i = 0;
                for (Map.Entry < String, Object > entry: map.entrySet()) {
                    System.out.println(entry.getKey() + " = " + entry.getValue());
                    i++;
                }
            }
        }
    }

}
