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
	void testConnection() throws Exception{
		DAO dao = new DAO();
		dao.connect();
		dao.close();
	}

	// @Test
	// void testUserJsonParse() throws Exception{
	// 	UserDAO userDAO = new UserDAO();
	// 	userDAO.connect();
	// 	User user = new User("Fulana da Silva" , "fulana@email.com" , "5531900000000");
	// 	userDAO.insert(user);
	// 	userDAO.update(user);
	// 	List<User> users = userDAO.getAll();
	// 	userDAO.close();
	// 	UserService u = new UserService(users);
	// 	assertNotNull(u.getJSON());
	// 	System.out.println(u.getJSON());

	// }

	// @Test
	// void testingPlanJsonParse() throws Exception {
	// 	PlanDAO planDAO = new PlanDAO();

	// 	Plan plan = new Plan("unlimited","unlimited",9999);
	// 	planDAO.connect();
	// 	planDAO.insert(plan);
	// 	planDAO.update(plan);
	// 	List<Plan> plans = planDAO.getAll();
	// 	planDAO.close();
	// 	PlanService planService = new PlanService();
	// 	assertNotNull(planService.getAllPlansInJson(plans));
	// 	System.out.println(planService.getAllPlansInJson(plans));

	// }

	// @Test

	// void testingSubscriptionJsonParse() throws Exception {
	// 	UUID uuid = UUID.randomUUID();
	// 	Timestamp timestamp = Timestamp.from(Instant.now());
	// 	SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
	// 	SubscriptionService subscriptionService = new SubscriptionService();
	// 	Subscription subscription = new Subscription(uuid,"5531900000000","plus",timestamp,true,timestamp);
	// 	subscriptionDAO.connect();
	// 	subscriptionDAO.insert(subscription);
	// 	//subscriptionDAO.update(subscription);
	// 	List<Subscription> subscriptions = subscriptionDAO.getAll();
	// 	subscriptionDAO.close();
	// 	subscriptionService.getAllSubscriptionsInJson(subscriptions);
	// 	assertNotNull(subscriptionService.getSubscriptionServiceJson());
	// 	System.out.println(subscriptionService.getSubscriptionServiceJson());

	// }

	// @Test

	// void testingQuestionJSONParse() throws Exception{
	// 	UUID uuid = UUID.randomUUID();
	// 	UUID uuid2 = UUID.fromString("602c9167-7114-4d3f-8ded-fc0fee75b30c");
	// 	Timestamp timestamp = Timestamp.from(Instant.now());
	// 	QuestionDAO questionDAO = new QuestionDAO();
	// 	QuestionService questionService = new QuestionService();
	// 	Question question = new Question(uuid,"Qual a idade do Silvio Santos?","Silvio Santos tem 88 anos de idade.",timestamp,uuid2);
	// 	questionDAO.connect();
	// 	questionDAO.insert(question);
	// //	questionDAO.update(question);
	// 	List<Question> questions = questionDAO.getAll();
	// 	questionDAO.close();
	// 	questionService.parseJson(questions);
	// 	System.out.println(questionService.getQuestionServiceJson());
	// 	assertNotNull(questionService.getQuestionServiceJson());
	// }

	// @Test
	// void adminConnectionTest() throws Exception {
	// 	UUID uuid = UUID.randomUUID();
	// 	AdminDAO adminDAO = new AdminDAO();
	// 	adminDAO.connect();
	// 	Admin admin = new Admin(uuid , "vi@email.com" , "1234");
	// 	adminDAO.insert(admin);
	// 	adminDAO.update(admin);
	// 	List<Admin> admins = adminDAO.getAll();
	// 	adminDAO.close();
	// 	AdminService adm = new AdminService(admins);
	// 	assertNotNull(adm.getJson());
	// 	System.out.println(adm.getJson());
	// }

}
