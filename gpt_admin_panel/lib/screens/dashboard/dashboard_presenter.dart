import 'package:flutter/cupertino.dart';
import 'package:gpt_admin_panel/core/HTTPClient.dart';

abstract class DashboardPresenter {
  String get healthStatus;
  Future<void> checkHealth();
  Future<int> fetchUsersNumber();
  void listen(Function() listener);
}

class DashboardPresenterImpl with ChangeNotifier implements DashboardPresenter {
  var _healthStatus = 'ok';

  @override
  Future<void> checkHealth() async {
    _healthStatus = 'loading';
    notifyListeners();

    const route = "/health";
    final client = HTTPClient(route);
    final response = await client.get();
    _healthStatus = response.body;
    notifyListeners();
  }

  @override
  Future<int> fetchUsersNumber() async {
    const route = "/users_count";
    final client = HTTPClient(route);
    final response = await client.get();
    return response.body as int;
  }

  @override
  String get healthStatus {
    return _healthStatus;
  }

  @override
  void listen(Function() listener) {
    addListener(listener);
  }
}
