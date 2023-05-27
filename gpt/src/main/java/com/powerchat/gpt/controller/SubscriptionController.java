package com.powerchat.gpt.controller;

import com.powerchat.gpt.Services.PlanService;
import com.powerchat.gpt.Services.SubscriptionService;
import com.powerchat.gpt.dao.PlanDAO;
import com.powerchat.gpt.dao.SubscriptionDAO;
import com.powerchat.gpt.model.Plan;
import com.powerchat.gpt.model.Subscription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SubscriptionController {
    @GetMapping("/subscriptions")
    public ResponseEntity<String> getSubscriptions() throws Exception {
        SubscriptionDAO dao = new SubscriptionDAO();
        dao.connect();
        List<Subscription> subscriptions = dao.getAll();
        String subscriptionsJson = new SubscriptionService().getAllSubscriptionsInJson(subscriptions);
        dao.close();
        return new ResponseEntity<>(subscriptionsJson, HttpStatus.OK);
    }

}
