package com.powerchat.gpt.model;

import com.powerchat.gpt.PowerChatHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserTests {
    @Test
    void init_setAllPropertiesCorrectly() {
        String name = "name";
        String email = "email";
        String phoneNumber = "phoneNumber";
        User user =  new User(name, email, phoneNumber);
        assertEquals(user.getName(), name);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getPhoneNumber(), phoneNumber);
    }

}
