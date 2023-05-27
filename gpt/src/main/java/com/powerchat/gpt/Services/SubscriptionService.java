package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.dao.SubscriptionDAO;
import com.powerchat.gpt.model.Subscription;
import com.powerchat.gpt.utils.CalendarManager;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionService {

    private String json;

    public String getSubscriptionServiceJson(){
        return json;
    }

    public String getAllSubscriptionsInJson(List<Subscription> subscriptions) throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        json = "{\n\"data\":[\n";
        for (int i = 0; i < subscriptions.size()-1; i++) {
            json += ow.writeValueAsString(subscriptions.get(i)) + ",\n";
        }
        json+=ow.writeValueAsString(subscriptions.get(subscriptions.size()-1)) + "]\n}";
        return json;
    }

    public Subscription createSubscription(String phoneNumber) {
        SubscriptionDAO dao = new SubscriptionDAO();
        dao.connect();
        Subscription subscription =
                new Subscription(UUID.randomUUID(),
                        phoneNumber, "free",
                Timestamp.from(Instant.now()),
                true,
                        CalendarManager.oneYearFromNow());
        dao.insert(subscription);
        dao.close();
        return subscription;
    }

}
