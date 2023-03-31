import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/ui/constants/app_spacing.dart';

class TitleMedium extends StatelessWidget {
  final String text;

  const TitleMedium(
    this.text, {
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(AppSpacing.m),
      child: Text(
        text,
        style: Theme.of(context).textTheme.titleMedium,
      ),
    );
  }
}
