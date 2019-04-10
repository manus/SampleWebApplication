package com.sample.web;


import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.Map;

public class HtmlHandler extends BaseHandler implements HttpHandler {


    public void handle(HttpExchange httpExchange) throws IOException {

        String htmlPath = httpExchange.getRequestURI().getPath();
        System.out.println("Requested Html [" + htmlPath + "]");
        String queryString = httpExchange.getRequestURI().getQuery();
        Map<String, String> queryMap = queryToMap(queryString);
        String imageName = "car.jpg";
        if(queryMap.containsKey("image")){
            imageName = queryMap.get("image");
        }
        System.out.println("Image [" + imageName + "]");
        File file = new File(basePath + htmlPath);
        byte [] bytearray  = new byte [(int)file.length()];
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read(bytearray, 0, bytearray.length);
        //prepare response
        String htmlString = new String(bytearray);
        System.out.println("Html string - " + htmlString);

        htmlString = htmlString.replaceAll("imageVariable", imageName);

        System.out.println("Modified Html string - " + htmlString);
        bytearray = htmlString.getBytes();
        String encoding = "UTF-8";
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=" + encoding);
        httpExchange.getResponseHeaders().set("Accept-Ranges", "bytes");
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(bytearray,0,bytearray.length);
        os.close();



    }


}
