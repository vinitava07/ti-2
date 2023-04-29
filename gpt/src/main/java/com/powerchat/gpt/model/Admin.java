package com.powerchat.gpt.model;

import java.util.UUID;

public class Admin {
    private final UUID id;
    private final String email;
    private final String password;

    public Admin(UUID id, String email , String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
