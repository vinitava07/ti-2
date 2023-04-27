package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.model.Question;
import com.powerchat.gpt.model.Subscription;

import java.util.List;

public class SubscriptionService {

    private String json;

    public String getSubscriptionServiceJson(){
        return json;
    }

    public void parseJson(List<Subscription> subscriptions) throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        for (int i = 0; i < subscriptions.size()-1; i++) {
            json += ow.writeValueAsString(subscriptions.get(i)) + ",\n";
        }
        json+=ow.writeValueAsString(subscriptions.get(subscriptions.size()-1));
    }


}
