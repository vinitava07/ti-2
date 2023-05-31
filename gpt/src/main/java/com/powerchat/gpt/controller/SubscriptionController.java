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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

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

    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<String> deleteSubscriptions(@PathVariable("id") String id) throws Exception {
        SubscriptionDAO dao = new SubscriptionDAO();
        dao.connect();
        UUID uuid = UUID.fromString(id);
        boolean isDeleted = dao.deleteByUserId(uuid);
        dao.close();
        if (isDeleted) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not ok", HttpStatus.OK);
        }
    }

    @PostMapping("/subscriptions/{id}")
    public ResponseEntity<String> disableSubscriptions(@PathVariable("id") String id) throws Exception {
        SubscriptionDAO dao = new SubscriptionDAO();
        UUID uuid = UUID.fromString(id);
        dao.connect();
        Subscription subscription = dao.getById(uuid);
        subscription.isActive = false;
        boolean isDisable = dao.update(subscription);
        dao.close();
        if (isDisable) {
            return new ResponseEntity<>("ok", HttpStatus.OK);

        }
        return new ResponseEntity<>("not ok", HttpStatus.OK);

    }
}
