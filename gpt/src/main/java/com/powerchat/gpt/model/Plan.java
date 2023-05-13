package com.powerchat.gpt.model;

import java.util.UUID;

public class Plan {

    public final String id;

    public final String name;

    public final int monthlyPromptLimit;

    public Plan(String id, String name, int monthlyPromptLimit) {
        this.id = id;
        this.name = name;
        this.monthlyPromptLimit = monthlyPromptLimit;
    }
}
