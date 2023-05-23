package com.powerchat.gpt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class FacebookMessageController{
    private String accessToken = "EAAKQ98n00ZBIBALEsbzkZC6RAJg3IZBMdwvdH5QfYEePQffJj2hjSrcZBILHgV8dVJgX60x4TiqSQNlQFasoliJf2aF1GeVjj4PZBZBiLYz0BKZAs3JYjfnylUQMEh2UGUIDElupG8zZAOEDOpGZBZBgO6X6ZAZCQcjF4MOqLhkrZCJ1OWzm5CteRYZBZA5DXdU1YI1f4k5x0ZBqemJn8NfZAtis1LngRiHZC8suLEr3kZD";
    private String phoneID = "106406395761880";

    public void sendReplyMessage(String recipientId, String messageText) {
        try {
            String requestBody = createTextRequestBody(recipientId, messageText);
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://graph.facebook.com/v14.0/" + phoneID + "/messages"))
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Did send" + response.body());
        } catch (Exception e) {
            System.out.println("Error sending reply message: " + e.getMessage());
        }
    }

    public void sendReplyImage(String recipientId, String assetURL) {
        try {
            System.out.println("Should send url as asset: " + assetURL);
            String requestBody = createImageRequestBody(recipientId, assetURL);
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://graph.facebook.com/v14.0/" + phoneID + "/messages"))
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Whatsapp response:" + response.toString());
            System.out.println("Whatsapp response body:" + response.body());
        } catch (Exception e) {
            System.out.println("Error sending reply message: " + e.getMessage());
        }
    }

    private String createTextRequestBody(String recipientId, String messageText) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode requestBodyJson = objectMapper.createObjectNode();
        requestBodyJson.put("messaging_product", "whatsapp");
        requestBodyJson.put("recipient_type", "individual");
        requestBodyJson.put("to", recipientId);
        requestBodyJson.put("type", "text");

        ObjectNode textJson = objectMapper.createObjectNode();
        textJson.put("body", messageText);
        requestBodyJson.put("text", textJson);

        return requestBodyJson.toString();
    }

    private String createImageRequestBody(String recipientId, String messageText) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode requestBodyJson = objectMapper.createObjectNode();
        requestBodyJson.put("messaging_product", "whatsapp");
        requestBodyJson.put("recipient_type", "individual");
        requestBodyJson.put("to", recipientId);
        requestBodyJson.put("type", "image");

        ObjectNode imageJson = objectMapper.createObjectNode();
        imageJson.put("link", messageText);
        requestBodyJson.put("image", imageJson);

        return requestBodyJson.toString();
    }
}