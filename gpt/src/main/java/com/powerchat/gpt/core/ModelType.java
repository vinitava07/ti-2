package com.powerchat.gpt.core;

public enum ModelType {
    image, text;
    public static ModelType from(String result){
        System.out.println("6: " + result);
        if (result.equals("image")) {
            return ModelType.image;
        }
        return ModelType.text;
    }
}
