import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/models/contact.dart';
import 'package:gpt_admin_panel/models/question.dart';
import 'package:gpt_admin_panel/screens/info/info_presenter.dart';
import 'package:gpt_admin_panel/ui/components/atoms/bordered_container.dart';
import 'package:gpt_admin_panel/ui/components/atoms/headline_medium.dart';
import 'package:gpt_admin_panel/ui/components/atoms/title_large.dart';
import 'package:gpt_admin_panel/ui/constants/app_colors.dart';
import 'package:gpt_admin_panel/ui/constants/app_spacing.dart';

enum InfoType { users, messages }

class InfoScreen extends StatefulWidget {
  final InfoType type;
  final InfoPresenter presenter = InfoPresenterImpl();

  InfoScreen({Key? key, required this.type}) : super(key: key);

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
        child: Center(child: buildListForType()),
      ),
    );
  }

  Widget buildListForType() {
    switch (widget.type) {
      case InfoType.messages:
        return buildMessageList();
      case InfoType.users:
        return buildUsersList();
    }
  }

  Widget buildMessageList() {
    return FutureBuilder(
      future: widget.presenter.getQuestions(),
      builder: (BuildContext context, AsyncSnapshot<QuestionList> snapshot) {
        if (snapshot.hasData) {
          final questionList = snapshot.data!;
          return Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const HeadlineMedium('PowerChat GPT - Admin'),
              TitleLarge(title),
              const Divider(),
              Flexible(
                child: ListView.separated(
                    itemBuilder: (BuildContext context, int index) {
                      final question = questionList.data.elementAt(index);
                      final prompt = question.prompt;
                      final reply = question.reply;
                      return ListTile(
                        title: Text(prompt),
                        subtitle: Text(reply),
                      );
                    },
                    separatorBuilder: (BuildContext context, int index) {
                      return const Divider();
                    },
                    itemCount: questionList.data.length),
              ),
            ],
          );
        } else if (snapshot.hasError) {
          return Text('Error on query');
        } else {
          return const CircularProgressIndicator();
        }
      },
    );
  }

  Widget buildUsersList() {
    return FutureBuilder(
      future: widget.presenter.getUsers(),
      builder: (BuildContext context, AsyncSnapshot<ContactList> snapshot) {
        if (snapshot.hasData) {
          final contactList = snapshot.data!;
          return Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const HeadlineMedium('PowerChat GPT - Admin'),
              TitleLarge(title),
              const Divider(),
              Flexible(
                child: ListView.separated(
                    itemBuilder: (BuildContext context, int index) {
                      final contact = contactList.data.elementAt(index);
                      final email = contact.email;
                      final name = contact.name;
                      return ListTile(
                        title: Text(email),
                        subtitle: Text(name),
                      );
                    },
                    separatorBuilder: (BuildContext context, int index) {
                      return const Divider();
                    },
                    itemCount: contactList.data.length),
              ),
            ],
          );
        } else if (snapshot.hasError) {
          return Text('Error on query');
        } else {
          return const CircularProgressIndicator();
        }
      },
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
