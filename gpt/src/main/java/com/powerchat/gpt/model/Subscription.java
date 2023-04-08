package com.powerchat.gpt.model;

import java.sql.Timestamp;
import java.util.UUID;

public class Subscription {
    final UUID id;

    final UUID userID;

    final UUID planID;

    final Timestamp createdAt;

    boolean isActive;

    final Timestamp expirationDate;

    Subscription(UUID id, UUID userID, UUID planID, Timestamp createdAt, boolean isActive, Timestamp expirationDate){
        this.id = id;
        this.userID = userID;
        this.planID = planID;
        this.createdAt = createdAt;
        this.expirationDate = expirationDate;
        this.isActive = isActive;
    }
}
