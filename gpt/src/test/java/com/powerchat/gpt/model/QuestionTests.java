package com.powerchat.gpt.model;

import com.powerchat.gpt.PowerChatHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QuestionTests {
    @Test
    void init_setAllPropertiesCorrectly() {

        UUID id = new UUID.randomUUID();
        String question = "question";
        String reply = "reply";
        TimeStamp createdAt = new TimeStamp(System.currentTimeMillis());
        UUID subscriptionId = new UUID.randomUUID();
        Question question1 = new Question(id,question,reply,createdAt,subscriptionId);
        assertEquals(question1.getId(),id);
        assertEquals(question1.getQuestion(),question);
        assertEquals(question1.getReply(),reply);
        assertEquals(question1.getCreatedAt(),createdAt);
        assertEquals(question1.getSubscriptionId(),subscriptionId);


    }
    @Test
    void init_setAllPropertiesCorrectly() {

        UUID id = new UUID.randomUUID();
        String question = "question";
        String reply = "reply";
        TimeStamp createdAt = new TimeStamp(System.currentTimeMillis());
        UUID subscriptionId = new UUID.randomUUID();
        Question question1 = new Question(id,question,createdAt,subscriptionId);
        assertEquals(question1.getReply(),"");
        question1.setReply(reply);
        assertEquals(question1.getReply(),reply);

    }

}