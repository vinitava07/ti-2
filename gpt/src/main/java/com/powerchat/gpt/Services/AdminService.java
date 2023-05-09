package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.model.Admin;

import java.util.List;

public class AdminService {

    private final List<Admin> admins;

    public  AdminService(List<Admin> admins) {
        this.admins = admins;
    }

    public String getJson() throws Exception{
        StringBuilder json = new StringBuilder();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        json.append("{\ndata:[\n");

        for (int i = 0; i < admins.size()-1; i++) {
            json.append(ow.writeValueAsString(admins.get(i))).append(",\n");
        }
        json.append(ow.writeValueAsString(admins.get(admins.size() - 1))+"]\n}");
        return json.toString();
    }
}