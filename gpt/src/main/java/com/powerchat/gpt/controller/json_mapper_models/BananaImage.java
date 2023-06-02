package com.powerchat.gpt.controller.json_mapper_models;

import java.util.List;
import java.util.Map;

record ModelOutput(List<String> images, Map<String, Object> parameters, String info) {}
public record BananaImage(String id, String message, long created, String apiVersion, String callID, boolean finished, List<ModelOutput> modelOutputs) {
    public String base64() {
        return modelOutputs.stream().findFirst().get().images().stream().findFirst().get();
    }
}
