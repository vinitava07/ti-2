package com.powerchat.gpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.RequestPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FacebookWebhookController {

    @PostMapping("meta-whatsapp-webhook")
    public ResponseEntity<String> getFacebookWebhookMessage(@RequestBody String payload) throws JsonProcessingException {
        System.out.println(payload);
        ObjectMapper objectMapper = new ObjectMapper();
        WhatsAppBusinessAccount waAccount = objectMapper.readValue(payload, WhatsAppBusinessAccount.class);
        String message = waAccount.getSentMessage();
        //TODO: - call python_bridge with AI model.
        //TODO: - call openAI api
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

record Profile(String name) {}
record Contact(Profile profile, String wa_id) {}
record Text(String body) {}
record Message(String from, String id, String timestamp, String type, Text text) {}
record Metadata(String display_phone_number, String phone_number_id) {}
record Value(String messaging_product, Metadata metadata, List<Contact> contacts, List<Message> messages) {}
record Change(String field, Value value) {}
record Entry(String id, List<Change> changes) {}
record WhatsAppBusinessAccount(String object, List<Entry> entry) {
    public String getSentMessage() {
        Entry entry = entry().stream().findFirst().get();
        Change change = entry.changes().stream().findFirst().get();
        Message message = change.value().messages().stream().findFirst().get();
        String body = message.text().body();
        return body;
    }
}
