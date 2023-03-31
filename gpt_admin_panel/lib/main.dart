import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/ui/constants/app_colors.dart';

void main() {
  runApp(const GPTAdminPanelApp());
}

/// Widget ->
/// Stateless ->
/// Statefull ->

class GPTAdminPanelApp extends StatelessWidget {
  const GPTAdminPanelApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: LoginScreen(),
    );
  }
}

class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.darkBG,
      body: Center(
        child: Container(
          height: 400,
          width: 320,
          decoration: BoxDecoration(
            color: AppColors.lightBG,
            borderRadius: BorderRadius.circular(18.0),
          ),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Text('PowerChat GPT'),
              const Text('Login'),
              Form(
                key: _formKey,
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Column(
                    children: [
                      emailField,
                      const SizedBox(height: 8.0),
                      passwordField
                    ],
                  ),
                ),
              ),
              //TEXTFIELD -> password
              loginButton,
            ],
          ),
        ),
      ),
    );
  }

  Widget get emailField {
    return TextFormField(
      keyboardType: TextInputType.emailAddress,
      decoration: const InputDecoration(
        labelText: 'email',
        border: OutlineInputBorder(),
      ),
      validator: (value) {
        if (value == null || value.isEmpty) {
          return 'Please enter email address';
        }
        return null;
      },
    );
  }

  Widget get passwordField {
    return TextFormField(
      keyboardType: TextInputType.visiblePassword,
      decoration: const InputDecoration(
        labelText: 'password',
        border: OutlineInputBorder(),
      ),
      validator: (value) {
        if (value == null || value.isEmpty) {
          return 'Please enter password';
        }
        return null;
      },
    );
  }

  Widget get loginButton {
    return MaterialButton(
      child: const Text('Login'),
      onPressed: () {
        if (_formKey.currentState?.validate() ?? false) {
          Navigator.of(context).push(
            MaterialPageRoute(
              builder: (context) {
                return const DashboardScreen();
              },
            ),
          );
        }
      },
    );
  }
}

class DashboardScreen extends StatelessWidget {
  const DashboardScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      body: Center(
        child: Text('Dashboard'),
      ),
    );
  }
}
