import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/screens/login/login_screen.dart';

void main() {
  runApp(const GPTAdminPanelApp());
}

class GPTAdminPanelApp extends StatelessWidget {
  const GPTAdminPanelApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      scrollBehavior: AppScrollBehavior(),
      title: 'Powerchat GPT - Admin',
      home: const LoginScreen(),
    );
  }
}

class AppScrollBehavior extends MaterialScrollBehavior {
  @override
  Set<PointerDeviceKind> get dragDevices => {
        PointerDeviceKind.touch,
        PointerDeviceKind.mouse,
      };
}
