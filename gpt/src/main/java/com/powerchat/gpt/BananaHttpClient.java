package com.powerchat.gpt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powerchat.gpt.controller.json_mapper_models.BananaImage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class BananaHttpClient {
    private HttpClient client;

    public BananaHttpClient() {
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    public BananaImage requestBananaDevCompletion(String prompt) throws IOException, InterruptedException {

        String t = "\t{\n" +
                "\t\t\"apiKey\": \"90a84b06-eb21-4e11-a581-89cac46d8832\",\n" +
                "\t\t\"modelKey\": \"f8d2d308-127a-4962-a823-a5baad5daf88\",\n" +
                "\t\t\"modelInputs\": {\n" +
                "\t\t\t\"endpoint\": \"txt2img\",\n" +
                "\t\t\t\"params\": {\n" +
                "\t\t\t\t\"prompt\": \"" + prompt + " low-angle viewUnreal engine 5, cinematic, low angle(((mdjrny-v4 style))), ((intricate)), ((highly detailed)), depth of field, masterpiece, award-winning, denoised, fine art, ((professionally color graded))\",\n" +
                "\t\t\t\t\"negativePrompt\": \"bad art\",\n" +
                "\t\t\t\t\"steps\": 25,\n" +
                "\t\t\t\t\"sampler_name\": \"Euler a\",\n" +
                "\t\t\t\t\"cfg_scale\": 7.5,\n" +
                "\t\t\t\t\"seed\": 42,\n" +
                "\t\t\t\t\"batch_size\": 1,\n" +
                "\t\t\t\t\"n_iter\": 1,\n" +
                "\t\t\t\t\"width\": 540,\n" +
                "\t\t\t\t\"height\": 540,\n" +
                "\t\t\t\t\"tilling\": true" +
                "\t\t\n}"+
                "\t\n}\n"+
                "}";
        System.out.println(t);

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.banana.dev/start/v4"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(t))
                .build();
        try {
            String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(body, BananaImage.class);
        } catch (Exception e) {
            System.out.println("Shit happened: " + e);
            throw e;
        }
    }
}

