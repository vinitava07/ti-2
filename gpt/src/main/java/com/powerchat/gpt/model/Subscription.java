package com.powerchat.gpt.model;

import java.sql.Timestamp;
import java.util.UUID;

public class Subscription {
    public final UUID id;

    public final String userID;

    public final String planID;

    public final Timestamp createdAt;

    public boolean isActive;

    public final Timestamp expirationDate;

    public Subscription(UUID id, String userID, String planID, Timestamp createdAt, boolean isActive, Timestamp expirationDate){
        this.id = id;
        this.userID = userID;
        this.planID = planID;
        this.createdAt = createdAt;
        this.expirationDate = expirationDate;
        this.isActive = isActive;
    }
}
