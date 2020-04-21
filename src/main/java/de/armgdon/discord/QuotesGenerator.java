package de.armgdon.discord;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class QuotesGenerator {
    private static HttpClient httpClient;
    private static String API_SOURCE = "http://loremricksum.com/api/?paragraphs=1&quotes=1";

    public QuotesGenerator() {
        httpClient = HttpClient.newHttpClient();
    }

    public static String generateQuote() {
        HttpRequest request = HttpRequest.newBuilder(URI.create(API_SOURCE))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return transformResponse(response);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    private static String transformResponse(HttpResponse<String> response) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(response.body());
            StringBuilder stringBuilder = new StringBuilder(object.get("data").toString());
            stringBuilder.deleteCharAt(0).deleteCharAt(stringBuilder.length() - 1);
            byte[] bytesArray = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
            String output = new String(bytesArray, StandardCharsets.UTF_8);
            return output;
        } catch (ParseException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }
}
