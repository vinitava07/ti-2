package com.powerchat.gpt.model;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SubscriptionTests {

    @Test
    void init_setAllPropertiesCorrectly() {
        UUID id = UUID.randomUUID();
        String userID = "User";
        String planID = "plan'";
        Timestamp createdAt = Timestamp.from(Instant.now());
        Timestamp expirationDate = Timestamp.from(Instant.now());
        boolean isActive = true;
        Subscription subs = new Subscription(id, userID,  planID, createdAt, isActive, expirationDate);
        assertEquals(subs.createdAt, createdAt);
        assertEquals(subs.id, id);
        assertEquals(subs.planID, planID);
        assertEquals(subs.userID, userID);
        assertEquals(subs.createdAt, createdAt);
        assertEquals(subs.isActive, isActive);
    }
}
