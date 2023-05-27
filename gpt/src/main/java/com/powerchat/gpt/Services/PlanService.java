package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.model.Plan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    public String getAllPlansInJson(List<Plan> plans) throws Exception{
        StringBuilder json = new StringBuilder();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        json.append("{\n\"data\":[\n");

        for (int i = 0; i < plans.size()-1; i++) {
            json.append(ow.writeValueAsString(plans.get(i))).append(",\n");
        }
        json.append(ow.writeValueAsString(plans.get(plans.size() - 1))+"]\n}");
        return json.toString();
    }

}
