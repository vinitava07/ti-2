import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/main.dart';
import 'package:gpt_admin_panel/screens/dashboard/dashboard_presenter.dart';
import 'package:gpt_admin_panel/screens/dashboard/dashboard_screen.dart';
import 'package:gpt_admin_panel/ui/components/atoms/title_large.dart';
import 'package:gpt_admin_panel/ui/components/atoms/primary_button.dart';
import 'package:gpt_admin_panel/ui/constants/app_colors.dart';
import 'package:gpt_admin_panel/ui/constants/app_radius.dart';
import 'package:gpt_admin_panel/ui/constants/app_spacing.dart';

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
            borderRadius: BorderRadius.circular(AppRadius.l),
          ),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Spacer(),
              const TitleLarge('PowerChat GPT Admin'),
              const Spacer(),
              Form(
                key: _formKey,
                child: Padding(
                  padding: EdgeInsets.symmetric(
                      horizontal: AppSpacing.l, vertical: AppSpacing.m),
                  child: Column(
                    children: [
                      emailField,
                      const SizedBox(height: 8.0),
                      passwordField
                    ],
                  ),
                ),
              ),
              loginButton,
              const Spacer(
                flex: 4,
              ),
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
      obscureText: true,
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
    return PrimaryButton(
      child: const Text('Login'),
      onPressed: () {
        if (_formKey.currentState?.validate() ?? false) {
          Navigator.of(context).push(
            MaterialPageRoute(
              builder: (context) {
                return DashboardScreen(
                  presenter: DashboardPresenterImpl(),
                );
              },
            ),
          );
        }
      },
    );
  }
}
