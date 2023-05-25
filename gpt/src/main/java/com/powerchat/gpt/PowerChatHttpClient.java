package com.powerchat.gpt;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PowerChatHttpClient {
	private HttpClient client;
	
	public PowerChatHttpClient() {
		client = HttpClient.newBuilder()
		        .version(Version.HTTP_1_1)
		        .followRedirects(Redirect.NORMAL)
		        .connectTimeout(Duration.ofSeconds(20))
		        .build();
	}

	public String requestOpenAICompletion(String question) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.openai.com/v1/completions"))
				.header("Authorization", "Bearer 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")
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString("\t{\n" +
						"\t\t\"model\": \"text-davinci-003\",\n" +
						"\t\t\"prompt\": \"" + "Returns the following question reply as a JSON in the following format: {your reply} the question is: " +question +"\",\n" +
						"\t\t\"temperature\": 0,\n" +
						"\t\t\"max_tokens\": 50\n" +
						"\t\t}"))
				.build();
		try {
			String response = client.send(request, BodyHandlers.ofString()).body();
			// Parse the JSON string
			JSONObject jsonObject = new JSONObject(response);
			String reply = jsonObject.getJSONArray("choices")
					.getJSONObject(0)
					.getString("text");
			JSONObject replyObject = new JSONObject(reply);
			String replyText = replyObject.getString("reply");
			System.out.println(replyText);
			return replyText;
		} catch (Exception e) {
			System.out.println("Shit happened: " + e);
		}
		return "";
	}
}
