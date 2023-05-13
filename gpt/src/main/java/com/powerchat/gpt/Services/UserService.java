package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.model.User;

import java.util.List;

public class UserService {

    private final List<User> users;

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
}
