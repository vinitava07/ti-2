import 'dart:async';
import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:gpt_admin_panel/core/HTTPClient.dart';
import 'package:gpt_admin_panel/models/contact.dart';
import 'package:http/http.dart' as http;

abstract class DashboardPresenter {
  String get serverHealthStatus;
  String get openAIHealth;
  String get formattedUserCount;
  Future<void> checkHealth({bool showLoadingLabel});
  Future<void> checkOpenAIHealth();
  Future<void> fetchUsersCount();
  void listen(Function() listener);
}

class DashboardPresenterImpl with ChangeNotifier implements DashboardPresenter {
  var _healthStatus = 'ok';
  var _openAIHealth = 'ok';
  var _amountOfUsers = '';

  DashboardPresenterImpl() {
    startTimer();
  }

  void startTimer() {
    Timer.periodic(const Duration(seconds: 1), (_) {
      checkHealth();
      fetchUsersCount();
    });
  }

  @override
  Future<void> checkHealth({showLoadingLabel = false}) async {
    if (showLoadingLabel) {
      _healthStatus = 'loading';
      notifyListeners();
    }

    const route = "/health";
    final client = HTTPClient(route);
    final response = await client.get();
    _healthStatus = response.body;
    notifyListeners();
  }

  @override
  Future<void> checkOpenAIHealth() async {
    _openAIHealth = 'loading';
    notifyListeners();

    const route = "/health-openAI";
    final client = HTTPClient(route);
    final response = await client.get();
    _openAIHealth = response.body;
    notifyListeners();
  }

  @override
  Future<void> fetchUsersCount() async {
    const path = "/user";
    final client = HTTPClient(path);
    http.Response response = await client.get();
    final usersJson = jsonDecode(response.body);
    final contactList = ContactList.fromJson(usersJson);
    _amountOfUsers = contactList.data.length.toString();
    notifyListeners();
  }

  @override
  String get serverHealthStatus {
    return _healthStatus;
  }

  @override
  String get openAIHealth {
    return _openAIHealth;
  }

  @override
  String get formattedUserCount {
    return _amountOfUsers;
  }

  @override
  void listen(Function() listener) {
    addListener(listener);
  }
}
