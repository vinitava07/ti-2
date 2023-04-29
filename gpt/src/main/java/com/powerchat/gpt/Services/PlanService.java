package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.model.Plan;

import java.util.List;

public class PlanService {

    private final List<Plan> plans;

    public  PlanService(List<Plan> plans) {
        this.plans = plans;
    }

    public String getJson() throws Exception{
        StringBuilder json = new StringBuilder();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        json.append("{\ndata:[\n");

        for (int i = 0; i < plans.size()-1; i++) {
            json.append(ow.writeValueAsString(plans.get(i))).append(",\n");
        }
        json.append(ow.writeValueAsString(plans.get(plans.size() - 1))+"]\n}");
        return json.toString();
    }
}
