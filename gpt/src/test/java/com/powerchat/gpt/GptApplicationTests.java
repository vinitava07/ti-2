package com.powerchat.gpt;

import com.powerchat.gpt.Services.UserService;
import com.powerchat.gpt.dao.UserDAO;
import com.powerchat.gpt.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
class GptApplicationTests {

	@Test
	void requestOpenAICompletionReturnsNonEmptyValue() {
		PowerChatHttpClient client = new PowerChatHttpClient();
		String response = client.requestOpenAICompletion();
		assertNotNull(response);
	}

	@Test
	void testJSONParse() throws Exception{
		UserDAO userDAO = new UserDAO();
		userDAO.connect();
		User user = new User("Alexandre Jurka" , "alex@email.com" , "(31) 99882-2143");
		userDAO.insert(user);
		userDAO.update(user);
		List<User> users = userDAO.getAll();

		UserService u = new UserService();
		u.parseJSON(users);
		assertNotNull(u.getJSON());
		System.out.println(u.getJSON());

	}

}
