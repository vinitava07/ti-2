package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.dao.SubscriptionDAO;
import com.powerchat.gpt.dao.UserDAO;
import com.powerchat.gpt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final List<User> users;

    @Autowired
    SubscriptionService subscriptionService;

    public UserService(List<User> users) {
        this.users = users;
    }

    public String getJSON() throws Exception {
        StringBuilder json = new StringBuilder();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        json.append("{\n\"data\":[\n");
        for (int i = 0; i < users.size() - 1; i++) {
            json.append(ow.writeValueAsString(users.get(i))).append(",\n");
        }
        json.append(ow.writeValueAsString(users.get(users.size() - 1)) + "]\n}");
        return json.toString();
    }

    public UUID createUserIfDoesNotExists(String name, String phoneNumber) {
        UUID uuid;
        UserDAO dao = new UserDAO();
        dao.connect();
        User existingUser = dao.getById(phoneNumber);
        if (existingUser == null) {
            User newUser = new User(name, "some@email.com", phoneNumber);
            dao.insert(newUser);
            dao.close();
            uuid = subscriptionService.createSubscription(phoneNumber).id;
        } else {
            dao.close();
            SubscriptionDAO subsDao = new SubscriptionDAO();
            subsDao.connect();
            uuid = subsDao.getByUserId(phoneNumber).id;
            subsDao.close();
        }
        return  uuid;
    }
}
