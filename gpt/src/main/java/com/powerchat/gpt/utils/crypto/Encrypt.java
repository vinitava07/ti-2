package com.powerchat.gpt.utils.crypto;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class Encrypt {

    public static String encrypt(String value) {

        return DigestUtils.md5DigestAsHex(value.getBytes(StandardCharsets.UTF_8));
    }
}
