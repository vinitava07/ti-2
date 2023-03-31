import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/ui/constants/app_colors.dart';

class PrimaryButton extends StatefulWidget {
  final Widget child;
  final Function onPressed;

  const PrimaryButton({Key? key, required this.child, required this.onPressed})
      : super(key: key);

  @override
  State<PrimaryButton> createState() => _PrimaryButtonState();
}

class _PrimaryButtonState extends State<PrimaryButton> {
  @override
  Widget build(BuildContext context) {
    //TODO: - user another UI component
    return ElevatedButton(
        style: ButtonStyle(
          backgroundColor: MaterialStatePropertyAll<Color>(AppColors.primary),
        ),
        onPressed: () => widget.onPressed(),
        child: widget.child);
  }
}
