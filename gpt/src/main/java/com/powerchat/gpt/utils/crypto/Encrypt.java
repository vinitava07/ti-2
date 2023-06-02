package com.powerchat.gpt.utils.crypto;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class Encrypt {

    public static String encrypt(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        return DigestUtils.md5DigestAsHex(bytes);
    }
}
