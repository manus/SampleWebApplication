package com.sample.web;

import java.util.HashMap;
import java.util.Map;

public class BaseHandler {

    protected String basePath = "/Users/msrivastava/IdeaProjects/SampleWebApplication/out/production/SampleWebApplication/";

    protected Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }
}
