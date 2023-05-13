package com.powerchat.gpt.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

public class PlanTests {

    @Test
    void init_setAllPropertiesCorrectly() {
        String id = "plan";
        String name = "test";
        int monthlyPromptLimit = 0;
        Plan plan = new Plan(id, name, monthlyPromptLimit);
        assertEquals(plan.id, id);
        assertEquals(plan.name, name);
        assertEquals(plan.monthlyPromptLimit, monthlyPromptLimit);
    }
}
