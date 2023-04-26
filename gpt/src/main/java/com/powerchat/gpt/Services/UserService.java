package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.model.User;

import java.util.List;

public class UserService {
    private String json;


    public String getJSON(){
        return json;
    }
    public void parseJSON(List<User> users)throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        json = ow.writeValueAsString(users.get(0));
    }
}
