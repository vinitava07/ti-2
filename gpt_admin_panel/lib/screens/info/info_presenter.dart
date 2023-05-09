import 'dart:convert';

import 'package:gpt_admin_panel/core/HTTPClient.dart';
import 'package:gpt_admin_panel/models/contact.dart';
import 'package:http/http.dart' as http;

abstract class InfoPresenter {
  Future<ContactList> getQuestions();
}

class InfoPresenterImpl implements InfoPresenter {
  @override
  Future<ContactList> getQuestions() async {
    const path = "/user";
    final client = HTTPClient(path);
    http.Response response = await client.get();
    final contactList = ContactList.fromJson(jsonDecode(response.body));
    return contactList;
  }
}
