import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/screens/login/login_screen.dart';

void main() {
  runApp(const GPTAdminPanelApp());
}

class GPTAdminPanelApp extends StatelessWidget {
  const GPTAdminPanelApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Powerchat GPT - Admin',
      home: LoginScreen(),
    );
  }
}
