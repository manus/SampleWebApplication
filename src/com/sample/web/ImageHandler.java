package com.sample.web;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.Map;

public class ImageHandler extends BaseHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath();

        System.out.println("Received request - " + path);
        String image = path.substring(path.lastIndexOf("/") + 1);
        System.out.println("Image requested [" + image + "]");
        String imageName = "images/" + image;
        try {
            File file = new File(basePath + imageName);
            byte [] bytearray  = new byte [(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(bytearray, 0, bytearray.length);
            //prepare response
            Headers h = httpExchange.getResponseHeaders();
            h.add("Content-Type", "image/jpeg");
            httpExchange.sendResponseHeaders(200, file.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(bytearray,0,bytearray.length);
            os.close();
        }
        catch(IOException e) {
            byte [] response = ("I wasn't able to load image - Got Exception [" + e.getMessage() + "]").getBytes();
            httpExchange.sendResponseHeaders(200, response.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(response);
            os.close();
        }






    }


}
