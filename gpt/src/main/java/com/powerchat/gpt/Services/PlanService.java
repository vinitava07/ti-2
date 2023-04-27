package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.model.Plan;

import java.util.List;

public class PlanService {

    private String json;

    public String getPlanServiceJson(){
        return json;
    }

    public void parseJson(List<Plan> plans) throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        for (int i = 0; i < plans.size()-1; i++) {
            json += ow.writeValueAsString(plans.get(i)) + ",\n";
        }
        json+=ow.writeValueAsString(plans.get(plans.size()-1));
    }

}
