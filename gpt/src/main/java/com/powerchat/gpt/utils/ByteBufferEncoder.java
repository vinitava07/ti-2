package com.powerchat.gpt.utils;

import java.nio.ByteBuffer;
import java.util.Base64;

public class ByteBufferEncoder {

    static public ByteBuffer fromBase64(String base64) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        return ByteBuffer.wrap(decodedBytes);
    }
}
