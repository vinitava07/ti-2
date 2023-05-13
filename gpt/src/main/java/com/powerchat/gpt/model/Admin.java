package com.powerchat.gpt.model;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Digest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
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

    public String getEncryptedPassword() {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }
}
