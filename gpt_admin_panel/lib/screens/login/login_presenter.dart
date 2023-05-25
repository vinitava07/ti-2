import 'dart:convert';

import 'package:gpt_admin_panel/core/HTTPClient.dart';
import 'package:http/http.dart' as http;

class LoginPresenter {
  Future<bool> login(String email, String password) async {
    const path = '/login';
    final client = HTTPClient(path);
    http.Response response = await client.post({'email':email,'password':password});
    return response.statusCode == 200;
  }
}
