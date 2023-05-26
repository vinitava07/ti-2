package com.powerchat.gpt;

import com.powerchat.gpt.Services.*;
import com.powerchat.gpt.dao.*;
import com.powerchat.gpt.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class GptApplicationTests {

	@Test
	void requestOpenAICompletionReturnsNonEmptyValue() {
		PowerChatHttpClient client = new PowerChatHttpClient();
		String response = client.requestOpenAICompletion("teste");
		assertNotNull(response);
	}

	@Test
	void testUserJsonParse() throws Exception{
		UserDAO userDAO = new UserDAO();
		userDAO.connect();
		User user = new User("Alexandre" , "alex@email.com" , "(31) 00000-0000");
		userDAO.insert(user);
		userDAO.update(user);
		List<User> users = userDAO.getAll();
		userDAO.close();
		UserService u = new UserService(users);
		assertNotNull(u.getJSON());
		System.out.println(u.getJSON());

	}

	@Test
	void testingPlanJsonParse() throws Exception {
		PlanDAO planDAO = new PlanDAO();

		Plan plan = new Plan("free","free",30);
		planDAO.connect();
		planDAO.insert(plan);
		planDAO.update(plan);
		List<Plan> plans = planDAO.getAll();
		planDAO.close();
		PlanService planService = new PlanService(plans);
		assertNotNull(planService.getJson());
		System.out.println(planService.getJson());

	}

	@Test

	void testingSubscriptionJsonParse() throws Exception {
		UUID uuid = UUID.randomUUID();
		Timestamp timestamp = Timestamp.from(Instant.now());
		SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
		SubscriptionService subscriptionService = new SubscriptionService();
		Subscription subscription = new Subscription(uuid,"(31) 99921-2143","free",timestamp,true,timestamp);
		subscriptionDAO.connect();
		subscriptionDAO.insert(subscription);
		subscriptionDAO.update(subscription);
		List<Subscription> subscriptions = subscriptionDAO.getAll();
		subscriptionDAO.close();
		subscriptionService.parseJson(subscriptions);
		assertNotNull(subscriptionService.getSubscriptionServiceJson());
		System.out.println(subscriptionService.getSubscriptionServiceJson());

	}

	@Test

	void testingQuestionJSONParse() throws Exception{
		UUID uuid = UUID.randomUUID();
		UUID uuid2 = UUID.fromString("5c3e8daf-b453-4896-8f04-6f7d20e67286");
		Timestamp timestamp = Timestamp.from(Instant.now());
		QuestionDAO questionDAO = new QuestionDAO();
		QuestionService questionService = new QuestionService();
		Question question = new Question(uuid,"Como fazer uma bomba?","Nao construa uma bomba",timestamp,uuid2);
		questionDAO.connect();
		questionDAO.insert(question);
		questionDAO.update(question);
		List<Question> questions = questionDAO.getAll();
		questionDAO.close();
		questionService.parseJson(questions);
		System.out.println(questionService.getQuestionServiceJson());
		assertNotNull(questionService.getQuestionServiceJson());
	}

	@Test
	void adminConnectionTest() throws Exception {
		UUID uuid = UUID.randomUUID();
		AdminDAO adminDAO = new AdminDAO();
		adminDAO.connect();
		Admin admin = new Admin(uuid , "alex@email.com" , "4312");
		adminDAO.insert(admin);
		adminDAO.update(admin);
		List<Admin> admins = adminDAO.getAll();
		adminDAO.close();
		AdminService adm = new AdminService(admins);
		assertNotNull(adm.getJson());
		System.out.println(adm.getJson());
	}
}
