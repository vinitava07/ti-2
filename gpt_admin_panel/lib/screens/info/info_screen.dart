import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/ui/components/atoms/bordered_container.dart';
import 'package:gpt_admin_panel/ui/components/atoms/headline_medium.dart';
import 'package:gpt_admin_panel/ui/components/atoms/title_large.dart';
import 'package:gpt_admin_panel/ui/constants/app_colors.dart';
import 'package:gpt_admin_panel/ui/constants/app_spacing.dart';

enum InfoType { users, messages }

class InfoScreen extends StatefulWidget {
  final InfoType type;

  const InfoScreen({Key? key, required this.type}) : super(key: key);

  @override
  State<InfoScreen> createState() => _InfoScreenState();
}

class _InfoScreenState extends State<InfoScreen> {
  String get title => widget.type == InfoType.users ? 'Usuários' : 'Perguntas';

  List<ListTile> get tiles {
    if (widget.type == InfoType.users) {
      return List<ListTile>.generate(
          100,
          (index) => const ListTile(
                title: Text('user name'),
                subtitle: Text('user plan'),
              ));
    } else {
      return List<ListTile>.generate(
        100,
        (index) => const ListTile(
          title: Text('Question'),
        ),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.darkBG,
      body: BorderedContainer(
        child: Center(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const HeadlineMedium('PowerChat GPT - Admin'),
              TitleLarge(title),
              const Divider(),
              Flexible(
                child: ListView.separated(
                    itemBuilder: (BuildContext context, int index) {
                      return tiles.elementAt(index);
                    },
                    separatorBuilder: (BuildContext context, int index) {
                      return const Divider();
                    },
                    itemCount: tiles.length),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget get infoList {
    return Padding(
      padding: EdgeInsets.all(AppSpacing.m),
      child: ListView(
        children: tiles,
      ),
    );
  }
}
