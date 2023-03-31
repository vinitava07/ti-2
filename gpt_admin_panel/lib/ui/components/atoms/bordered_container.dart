import 'package:gpt_admin_panel/ui/constants/app_colors.dart';
import 'package:gpt_admin_panel/ui/constants/app_radius.dart';
import 'package:gpt_admin_panel/ui/constants/app_spacing.dart';
import 'package:flutter/material.dart';

class BorderedContainer extends StatelessWidget {
  final Widget child;
  final EdgeInsetsGeometry? padding;

  const BorderedContainer({Key? key, required this.child, this.padding})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
        padding: padding,
        margin: EdgeInsets.all(AppSpacing.l),
        decoration: BoxDecoration(
            color: Colors.white,
            border: Border.all(color: AppColors.darkBG.withOpacity(0.5)),
            borderRadius: BorderRadius.circular(AppRadius.s)),
        child: child);
  }
}
