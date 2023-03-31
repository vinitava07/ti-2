import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/screens/info/info_screen.dart';
import 'package:gpt_admin_panel/ui/components/atoms/bordered_container.dart';
import 'package:gpt_admin_panel/ui/components/atoms/headline_medium.dart';
import 'package:gpt_admin_panel/ui/components/atoms/title_large.dart';
import 'package:gpt_admin_panel/ui/components/atoms/title_medium.dart';
import 'package:gpt_admin_panel/ui/components/molecules/medium_card.dart';
import 'package:gpt_admin_panel/ui/constants/app_colors.dart';

class DashboardScreen extends StatelessWidget {
  const DashboardScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.darkBG,
      body: BorderedContainer(
        child: Center(
          child: Column(
            children: [serviceMetrics(context)],
          ),
        ),
      ),
    );
  }

  Widget serviceMetrics(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const HeadlineMedium('PowerChat GPT - Admin'),
        const TitleLarge('Métricas de uso'),
        Padding(
          padding: EdgeInsets.symmetric(horizontal: 12.0),
          child: Row(
            children: const [
              TitleMedium('Usuários ativos: 2932'),
              TitleMedium('Requisições p/ min: 64'),
              TitleMedium('Usuários pagantes: 1294'),
            ],
          ),
        ),
        const SizedBox(
          height: 40,
        ),
        const TitleLarge('Métricas de serviço'),
        Row(
          children: [
            MediumCard(text: 'Health check: ok', onTap: () {}),
            MediumCard(text: 'OpenAI integration: ok', onTap: () {}),
            MediumCard(text: 'FB Webhook: ok', onTap: () {}),
            MediumCard(text: 'FB GraphQL: ok', onTap: () {}),
          ],
        ),
        const SizedBox(
          height: 40,
        ),
        const TitleLarge('Informações'),
        Row(
          children: [
            MediumCard(
                text: 'Usuários',
                onTap: () {
                  Navigator.of(context)
                      .push(MaterialPageRoute(builder: (BuildContext context) {
                    return const InfoScreen(type: InfoType.users);
                  }));
                }),
            MediumCard(
                text: 'Perguntas',
                onTap: () {
                  Navigator.of(context)
                      .push(MaterialPageRoute(builder: (BuildContext context) {
                    return const InfoScreen(type: InfoType.messages);
                  }));
                }),
          ],
        ),
      ],
    );
  }
}
