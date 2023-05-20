package com.powerchat.gpt.core;

public enum ModelType {
    image, text;
    public static ModelType from(String result){
        if (result.contains("image")) {
            return ModelType.image;
        }
        return ModelType.text;
    }
}
