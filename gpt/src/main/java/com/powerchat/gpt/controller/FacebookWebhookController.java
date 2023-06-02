package com.powerchat.gpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.RequestPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.powerchat.gpt.BananaHttpClient;
import com.powerchat.gpt.PowerChatHttpClient;
import com.powerchat.gpt.Services.QuestionService;
import com.powerchat.gpt.Services.UserService;
import com.powerchat.gpt.core.ModelType;
import com.powerchat.gpt.core.PythonBridge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class FacebookWebhookController {

    @Autowired
    FacebookMessageController messageController;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @PostMapping("meta-whatsapp-webhook")
    public ResponseEntity<String> getFacebookWebhookMessage(@RequestBody String payload) throws Exception {
        System.out.println(payload);
        ObjectMapper objectMapper = new ObjectMapper();
        WhatsAppBusinessAccount waAccount = objectMapper.readValue(payload, WhatsAppBusinessAccount.class);
        String message = waAccount.getSentMessage();
        UUID subscriptionID = userService.createUserIfDoesNotExists(waAccount.getName(), waAccount.getPhoneNumber());
        String phoneNumber = waAccount.getPhoneNumber();
        process(message, subscriptionID, phoneNumber);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    private void process(String message, UUID subscriptionID, String phoneNumber) throws Exception {
        ModelType type = PythonBridge.classify(message);
        switch (type) {
            case text -> {
                PowerChatHttpClient powerChatHttpClient = new PowerChatHttpClient();
                String gptResponse = powerChatHttpClient.requestOpenAICompletion(message);
                System.out.println(gptResponse);
                messageController = new FacebookMessageController();
                questionService.storeQuestion(message, gptResponse, subscriptionID);
                messageController.sendReplyMessage(phoneNumber, gptResponse);
            }
            case image -> {
                BananaHttpClient bananaHttpClient = new BananaHttpClient();
                //String bananaResponse = bananaHttpClient.requestBananaDevCompletion(message);
                //CALL S3 to host.
                System.out.println("calling image");
                messageController.sendReplyMessage(phoneNumber, "Uma imagem está sendo gerada");
            }
        }
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

    public String getName() {
        Entry entry = entry().stream().findFirst().get();
        Change change = entry.changes().stream().findFirst().get();
        Contact contact = change.value().contacts().stream().findFirst().get();
        return contact.profile().name();
    }

    public String getPhoneNumber() {
        Entry entry = entry().stream().findFirst().get();
        Change change = entry.changes().stream().findFirst().get();
        Message message = change.value().messages().stream().findFirst().get();
        String phoneNumber = message.from();
        return phoneNumber;
    }
}
