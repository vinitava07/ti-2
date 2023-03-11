package com.powerchat.gpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.http.HttpClient;

@SpringBootApplication
public class GptApplication {

	public static void main(String[] args) {
		SpringApplication.run(GptApplication.class, args);
		requestOpenAICompletion();
	}

	static void requestOpenAICompletion() {
		PowerChatHttpClient client = new PowerChatHttpClient();
		String response = client.requestOpenAICompletion();
		System.out.println(response);
	}

}
