package com.powerchat.gpt.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuestionTests {
    @Test
    void init_setAllPropertiesCorrectly() {

        UUID id = UUID.randomUUID();
        String question = "question";
        String reply = "reply";
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        UUID subscriptionId = UUID.randomUUID();
        Question question1 = new Question(id,question,reply,createdAt,subscriptionId);
        assertEquals(question1.getId(),id);
        assertEquals(question1.getQuestion(),question);
        assertEquals(question1.getReply(),reply);
        assertEquals(question1.getCreatedAt(),createdAt);
        assertEquals(question1.getSubscriptionId(),subscriptionId);


    }
    @Test
    void init_setReplyChangesValuesCorrectly() {

        UUID id = UUID.randomUUID();
        String question = "question";
        String reply = "reply";
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        UUID subscriptionId = UUID.randomUUID();
        Question question1 = new Question(id,question,createdAt,subscriptionId);
        assertEquals(question1.getReply(),"");
        question1.setReply(reply);
        assertEquals(question1.getReply(),reply);

    }

}