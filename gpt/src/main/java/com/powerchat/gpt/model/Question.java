package com.powerchat.gpt.model;

import java.util.UUID;

import java.sql.Timestamp;

import java.util.Date;

public class Question {

    private final UUID id;
    private final String question;

    private String reply;

    private final Timestamp createdAt;

    private final UUID subscriptionId;

    public Question(UUID id, String question, String reply, Timestamp createdAt, UUID subscriptionId) {

        this.id = id;
        this.question = question;
        this.reply = reply;
        this.createdAt = createdAt;
        this.subscriptionId = subscriptionId;
    }

    public Question(UUID id, String question, Timestamp createdAt, UUID subscriptionId) {

        this.id = id;
        this.question = question;
        this.reply = "";
        this.createdAt = createdAt;
        this.subscriptionId = subscriptionId;
    }

    public UUID getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getReply() {
        return reply;
    }
    public void setReply(String reply) {
        this.reply = reply;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public UUID getSubscriptionId() {
        return subscriptionId;
    }
}