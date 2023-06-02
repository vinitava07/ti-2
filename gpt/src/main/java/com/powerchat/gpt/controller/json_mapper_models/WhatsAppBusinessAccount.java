package com.powerchat.gpt.controller.json_mapper_models;

import java.util.List;

record Profile(String name) {}
record Contact(Profile profile, String wa_id) {}
record Text(String body) {}
record Message(String from, String id, String timestamp, String type, Text text) {}
record Metadata(String display_phone_number, String phone_number_id) {}
record Value(String messaging_product, Metadata metadata, List<Contact> contacts, List<Message> messages) {}
record Change(String field, Value value) {}
record Entry(String id, List<Change> changes) {}
public record WhatsAppBusinessAccount(String object, List<Entry> entry) {
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
