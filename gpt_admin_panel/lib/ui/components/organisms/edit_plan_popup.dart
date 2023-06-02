import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/ui/constants/app_colors.dart';

class EditPlanPopup extends StatelessWidget {
  final String planName;
  final Function(String) onTap;

  const EditPlanPopup({Key? key, required this.planName, required this.onTap})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    final textController = TextEditingController();
    return AlertDialog(
      content: SizedBox(
        height: 440,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Spacer(),
            Text(
              "Editar limite mensal de perguntas",
              style: Theme.of(context).textTheme.headlineSmall,
            ),
            Text("plano: $planName"),
            const Spacer(),
            TextField(
              controller: textController,
            ),
            const Spacer(),
            MaterialButton(
              onPressed: () {
                final newValue = textController.text;
                if (newValue.isNotEmpty) {
                  onTap(newValue);
                }
              },
              color: AppColors.primary,
              child: const Text('Salvar'),
            ),
            const Spacer(),
          ],
        ),
      ),
    );
  }
}
