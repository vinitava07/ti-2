package com.powerchat.gpt;

import com.powerchat.gpt.dao.DAO;
import com.powerchat.gpt.dao.UserDAO;
import com.powerchat.gpt.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.http.HttpClient;

@SpringBootApplication
public class GptApplication {

	public static void main(String[] args) {
		SpringApplication.run(GptApplication.class, args);
		// requestOpenAICompletion();

		UserDAO user = new UserDAO();
		user.connect();
		User user1 = new User("Alexandre Jurka" , "alex@email.com" , "(31) 99882-2122");
		user.insert(user1);
	}

	static void requestOpenAICompletion() {
		PowerChatHttpClient client = new PowerChatHttpClient();
		String response = client.requestOpenAICompletion();
		System.out.println(response);
	}

}
