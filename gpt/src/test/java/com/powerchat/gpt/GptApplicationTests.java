package com.powerchat.gpt;

import com.powerchat.gpt.Services.PlanService;
import com.powerchat.gpt.Services.QuestionService;
import com.powerchat.gpt.Services.SubscriptionService;
import com.powerchat.gpt.Services.UserService;
import com.powerchat.gpt.dao.PlanDAO;
import com.powerchat.gpt.dao.QuestionDAO;
import com.powerchat.gpt.dao.SubscriptionDAO;
import com.powerchat.gpt.dao.UserDAO;
import com.powerchat.gpt.model.Plan;
import com.powerchat.gpt.model.Question;
import com.powerchat.gpt.model.Subscription;
import com.powerchat.gpt.model.User;
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
		UserService u = new UserService(users);
		assertNotNull(u.getJSON());
		System.out.println(u.getJSON());

	}

	@Test
	void testingPlanJsonParse() throws Exception {
		PlanDAO planDAO = new PlanDAO();

		Plan plan = new Plan("3","free",30);
		planDAO.connect();
		planDAO.insert(plan);
		planDAO.update(plan);
		List<Plan> plans = planDAO.getAll();
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
		Subscription subscription = new Subscription(uuid,"(31) 99882-2143","2",timestamp,true,timestamp);
		subscriptionDAO.connect();
		subscriptionDAO.insert(subscription);
		subscriptionDAO.update(subscription);
		List<Subscription> subscriptions = subscriptionDAO.getAll();
		subscriptionService.parseJson(subscriptions);
		assertNotNull(subscriptionService.getSubscriptionServiceJson());
		System.out.println(subscriptionService.getSubscriptionServiceJson());

	}

	@Test

	void testingQuestionJSONParse() throws Exception{
		UUID uuid = UUID.randomUUID();
		UUID uuid2 = UUID.fromString("48eb0581-03eb-4f41-8f03-a2a215d1280e");
		Timestamp timestamp = Timestamp.from(Instant.now());
		QuestionDAO questionDAO = new QuestionDAO();
		QuestionService questionService = new QuestionService();
		Question question = new Question(uuid,"Como fazer uma bomba?","Nao construa uma bomba",timestamp,uuid2);
		questionDAO.connect();
		questionDAO.insert(question);
		questionDAO.update(question);
		List<Question> questions = questionDAO.getAll();
		questionService.parseJson(questions);
		assertNotNull(questionService.getQuestionServiceJson());
		System.out.println(questionService.getQuestionServiceJson());
	}
}
