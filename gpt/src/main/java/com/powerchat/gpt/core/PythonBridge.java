package com.powerchat.gpt.core;

import java.io.*;


public class PythonBridge {
    private final static String scriptsPath = "src/main/java/com/powerchat/gpt/core";

    public static ModelType classify(String prompt) throws  Exception{
        String pythonScriptPath = scriptsPath + "model_classifier.py";
        ProcessBuilder processBuilder = new ProcessBuilder("python3", pythonScriptPath, prompt);
        Process process = processBuilder.start();
        String result = readProcessOutput(process);
        return ModelType.from(result);
    }

    private static String readProcessOutput(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder base64String = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            base64String.append(line);
        }
        return base64String.toString();
    }
}
