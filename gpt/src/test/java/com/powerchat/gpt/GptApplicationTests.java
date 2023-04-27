package com.powerchat.gpt;

import com.powerchat.gpt.Services.PlanService;
import com.powerchat.gpt.Services.SubscriptionService;
import com.powerchat.gpt.Services.UserService;
import com.powerchat.gpt.dao.PlanDAO;
import com.powerchat.gpt.dao.SubscriptionDAO;
import com.powerchat.gpt.dao.UserDAO;
import com.powerchat.gpt.model.Plan;
import com.powerchat.gpt.model.Subscription;
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
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

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
	void testUserJsonParse() throws Exception{
		UserDAO userDAO = new UserDAO();
		userDAO.connect();
		User user = new User("Alexandre Jurka" , "alex@email.com" , "(31) 98882-2157");
		userDAO.insert(user);
		userDAO.update(user);
		List<User> users = userDAO.getAll();
		UserService u = new UserService();
		u.parseJSON(users);
		assertNotNull(u.getUserJson());
		System.out.println(u.getUserJson());

	}
	@Test

	void testingPlanJsonParse() throws Exception {
		PlanDAO planDAO = new PlanDAO();
		PlanService planService = new PlanService();
		Plan plan = new Plan("3","free",30);
		planDAO.connect();
		planDAO.insert(plan);
		planDAO.update(plan);
		List<Plan> plans = planDAO.getAll();
		planService.parseJson(plans);
		assertNotNull(planService.getPlanServiceJson());
		System.out.println(planService.getPlanServiceJson());

	}

	@Test

	void testingSubscriptionJsonParse() throws Exception {
		UUID uuid = UUID.randomUUID();
		Timestamp timestamp = Timestamp.from(Instant.now());
		SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
		SubscriptionService subscriptionService = new SubscriptionService();
		Subscription subscription = new Subscription(uuid,"(31) 99882-2143","2",timestamp,true,timestamp);
		subscriptionDAO.connect();
		subscriptionDAO.insert(subscription);
		subscriptionDAO.update(subscription);
		List<Subscription> subscriptions = subscriptionDAO.getAll();
		subscriptionService.parseJson(subscriptions);
		assertNotNull(subscriptionService.getSubscriptionServiceJson());
		System.out.println(subscriptionService.getSubscriptionServiceJson());

	}

}
