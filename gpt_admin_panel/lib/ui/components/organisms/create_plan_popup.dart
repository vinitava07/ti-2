import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/ui/constants/app_colors.dart';

class CreatePlanPopup extends StatelessWidget {
  final Function(String, String) onCreate;

  const CreatePlanPopup({Key? key, required this.onCreate}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final nameController = TextEditingController();
    final limitContoller = TextEditingController();
    return AlertDialog(
      content: SizedBox(
        height: 440,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Spacer(),
            Text(
              "Criar novo plano:",
              style: Theme.of(context).textTheme.headlineSmall,
            ),
            const Spacer(),
            TextField(
              decoration: const InputDecoration(hintText: 'Nome'),
              controller: nameController,
            ),
            const Spacer(),
            TextField(
              decoration:
                  const InputDecoration(hintText: 'Limite de perguntas mensal'),
              controller: limitContoller,
            ),
            MaterialButton(
              onPressed: () {
                final name = nameController.text;
                final limit = limitContoller.text;
                if (name.isNotEmpty && limit.isNotEmpty) {
                  onCreate(name, limit);
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
