package com.powerchat.gpt;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class PowerChatHttpClient {
	private HttpClient client;
	
	PowerChatHttpClient() {
		client = HttpClient.newBuilder()
		        .version(Version.HTTP_1_1)
		        .followRedirects(Redirect.NORMAL)
		        .connectTimeout(Duration.ofSeconds(20))
		        .build();
	}

	String requestOpenAICompletion() {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.openai.com/v1/completions"))
				.header("Authorization", "Bearer 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")
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString("\t{\n" +
						"\t\t\"model\": \"text-davinci-003\",\n" +
						"\t\t\"prompt\": \"me diga por que deveria estudar ciencia da computacao em 10 palavras\",\n" +
						"\t\t\"temperature\": 0,\n" +
						"\t\t\"max_tokens\": 50\n" +
						"\t\t}"))
				.build();
		try {
			return client.send(request, BodyHandlers.ofString()).body();
		} catch (Exception e) {
			System.out.println("Shit happened: " + e);
		}
		return "";
	}
}
