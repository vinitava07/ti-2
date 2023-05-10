import 'dart:convert';

import 'package:gpt_admin_panel/core/HTTPClient.dart';
import 'package:gpt_admin_panel/models/contact.dart';
import 'package:gpt_admin_panel/models/question.dart';
import 'package:http/http.dart' as http;

abstract class InfoPresenter {
  Future<ContactList> getUsers();
  Future<QuestionList> getQuestions();
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
}
