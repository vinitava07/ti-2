package com.powerchat.gpt.model;

import java.util.UUID;

public class Plan {

    final UUID id;

    final String name;

    final int monthlyPromptLimit;

    public Plan(UUID id, String name, int monthlyPromptLimit) {
        this.id = id;
        this.name = name;
        this.monthlyPromptLimit = monthlyPromptLimit;
    }
}
