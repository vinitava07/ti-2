import 'dart:convert';

import 'package:gpt_admin_panel/core/HTTPClient.dart';
import 'package:gpt_admin_panel/models/contact.dart';
import 'package:gpt_admin_panel/models/plan.dart';
import 'package:gpt_admin_panel/models/question.dart';
import 'package:gpt_admin_panel/models/subscriptions.dart';
import 'package:http/http.dart' as http;

abstract class InfoPresenter {
  Future<ContactList> getUsers();
  Future<QuestionList> getQuestions();
  Future<PlanList> getPlans();
  Future<SubscriptionList> getSubscriptions();
  Future<void> deleteSubscription(String id);
  Future<void> disableSubscription(String id);
  Future<void> setNewPlanLimit(String name, String newLimit);
  Future<void> createNewPlan(String name, String newLimit);
}

class InfoPresenterImpl implements InfoPresenter {
  @override
  Future<ContactList> getUsers() async {
    const path = "/user";
    final client = HTTPClient(path);
    http.Response response = await client.get();
    final usersJson = jsonDecode(response.body);
    final contactList = ContactList.fromJson(usersJson);
    return contactList;
  }

  @override
  Future<QuestionList> getQuestions() async {
    const path = "/question";
    final client = HTTPClient(path);
    http.Response response = await client.get();
    final questionsJSON = jsonDecode(response.body);
    final questionsList = QuestionList.fromJson(questionsJSON);
    return questionsList;
  }

  @override
  Future<PlanList> getPlans() async {
    const path = "/plans";
    final client = HTTPClient(path);
    http.Response response = await client.get();
    final plansJSON = jsonDecode(response.body);
    final plansList = PlanList.fromJson(plansJSON);
    plansList.data
        .sort((l, r) => l.monthlyPromptLimit < r.monthlyPromptLimit ? -1 : 1);
    return plansList;
  }

  @override
  Future<SubscriptionList> getSubscriptions() async {
    const path = "/subscriptions";
    final client = HTTPClient(path);
    http.Response response = await client.get();
    final subscriptionsJSON = jsonDecode(response.body);
    final subscriptionsList = SubscriptionList.fromJson(subscriptionsJSON);
    return subscriptionsList;
  }

  @override
  Future<void> deleteSubscription(String id) async {
    final path = "/subscriptions/$id";
    final client = HTTPClient(path);
    await client.delete();
    return;
  }

  @override
  Future<void> disableSubscription(String id) async {
    final path = "/subscriptions/$id";
    final client = HTTPClient(path);
    await client.post({});
    return;
  }

  @override
  Future<void> setNewPlanLimit(String id, String newLimit) async {
    const path = "/update_plan";
    final client = HTTPClient(path);
    await client.post({"id": id, "monthly_prompt_limit": int.parse(newLimit)});
    return;
  }

  @override
  Future<void> createNewPlan(String name, String newLimit) async {
    const path = "/create_plan";
    final client = HTTPClient(path);
    await client.post({
      "id": name,
      "name": name,
      "monthly_prompt_limit": int.parse(newLimit)
    });
    return;
  }
}
