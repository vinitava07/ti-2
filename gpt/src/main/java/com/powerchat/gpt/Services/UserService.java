package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.model.User;

import java.util.List;

public class UserService {
    private String json;



    public UserService() {
        this.json = "";
    }
    public String getUserJson(){
        return json;
    }
    public void parseJSON(List<User> users)throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        for (int i = 0; i < users.size()-1; i++) {
            json += ow.writeValueAsString(users.get(i)) + ",\n";
        }
        json+=ow.writeValueAsString(users.get(users.size()-1));
    }
}
