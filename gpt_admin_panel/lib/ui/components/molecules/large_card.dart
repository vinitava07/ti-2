import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/ui/constants/app_spacing.dart';

class LargeCard extends StatelessWidget {
  final String text;
  final Function onTap;

  const LargeCard({super.key, required this.text, required this.onTap});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 180,
      width: 280,
      margin: EdgeInsets.all(AppSpacing.l),
      child: ElevatedButton(
        onPressed: () => onTap(),
        child: Text(text),
      ),
    );
  }
}
