import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/ui/constants/app_spacing.dart';

class MediumCard extends StatelessWidget {
  final String text;
  final Function onTap;

  const MediumCard({super.key, required this.text, required this.onTap});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 120,
      width: 180,
      margin: EdgeInsets.all(AppSpacing.m),
      child: ElevatedButton(
        onPressed: () => onTap(),
        child: Text(text),
      ),
    );
  }
}
