import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/models/contact.dart';
import 'package:gpt_admin_panel/models/plan.dart';
import 'package:gpt_admin_panel/models/question.dart';
import 'package:gpt_admin_panel/models/subscriptions.dart';
import 'package:gpt_admin_panel/screens/info/info_presenter.dart';
import 'package:gpt_admin_panel/ui/components/atoms/bordered_container.dart';
import 'package:gpt_admin_panel/ui/components/atoms/headline_medium.dart';
import 'package:gpt_admin_panel/ui/components/atoms/title_large.dart';
import 'package:gpt_admin_panel/ui/components/molecules/list_tiles/plan_list_tile.dart';
import 'package:gpt_admin_panel/ui/components/organisms/create_plan_popup.dart';
import 'package:gpt_admin_panel/ui/constants/app_colors.dart';

enum InfoType { users, messages, plans, subscriptions }

class InfoScreen extends StatefulWidget {
  final InfoType type;
  final InfoPresenter presenter = InfoPresenterImpl();

  InfoScreen({Key? key, required this.type}) : super(key: key);

  @override
  State<InfoScreen> createState() => _InfoScreenState();
}

class _InfoScreenState extends State<InfoScreen> {
  String get title {
    switch (widget.type) {
      case InfoType.users:
        return 'Usuários';
      case InfoType.messages:
        return 'Perguntas';
      case InfoType.plans:
        return 'Planos';
      case InfoType.subscriptions:
        return 'Assinaturas';
    }
  }

  Widget get progressIndicator {
    return const Center(
      child: CircularProgressIndicator(),
    );
  }

  Widget get errorOnQuery {
    return const Text('Error on query');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: AppColors.darkBG,
      ),
      backgroundColor: AppColors.darkBG,
      body: BorderedContainer(
        child: Center(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const HeadlineMedium('PowerChat GPT - Admin'),
              Row(
                children: [
                  TitleLarge(title),
                  const Spacer(),
                  addPlanButtonIfCase,
                ],
              ),
              const Divider(),
              Flexible(
                child: buildListForType(),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget get addPlanButtonIfCase {
    switch (widget.type) {
      case InfoType.plans:
        return IconButton(
            onPressed: () {
              showDialog(
                context: context,
                builder: (BuildContext context) => CreatePlanPopup(
                  onCreate: (name, limit) {
                    widget.presenter.createNewPlan(name, limit).then((_) {
                      Navigator.of(context).pop();
                      setState(() {});
                    });
                  },
                ),
              );
            },
            icon: const Icon(Icons.add));
      default:
        return const SizedBox();
    }
  }

  Widget buildListForType() {
    switch (widget.type) {
      case InfoType.messages:
        return buildMessageList();
      case InfoType.users:
        return buildUsersList();
      case InfoType.plans:
        return buildPlansList();
      case InfoType.subscriptions:
        return buildSubscriptionsList();
    }
  }

  Widget buildMessageList() {
    return FutureBuilder(
      future: widget.presenter.getQuestions(),
      builder: (BuildContext context, AsyncSnapshot<QuestionList> snapshot) {
        if (snapshot.hasData) {
          final questionList = snapshot.data!;
          return ListView.separated(
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
              itemCount: questionList.data.length);
        } else if (snapshot.hasError) {
          return errorOnQuery;
        } else {
          return progressIndicator;
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
          return ListView.separated(
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
              itemCount: contactList.data.length);
        } else if (snapshot.hasError) {
          return errorOnQuery;
        } else {
          return progressIndicator;
        }
      },
    );
  }

  Widget buildPlansList() {
    return FutureBuilder(
      future: widget.presenter.getPlans(),
      builder: (BuildContext context, AsyncSnapshot<PlanList> snapshot) {
        if (snapshot.hasData) {
          final planList = snapshot.data!;
          return ListView.separated(
              itemBuilder: (BuildContext context, int index) {
                final plan = planList.data.elementAt(index);
                final name = plan.name;
                final monthLimit = plan.monthlyPromptLimit;
                return PlanListTile(
                    presenter: widget.presenter,
                    name: name,
                    monthLimit: monthLimit,
                    onReload: () {
                      Navigator.of(context).pop();
                      setState(() {});
                    });
              },
              separatorBuilder: (BuildContext context, int index) {
                return const Divider();
              },
              itemCount: planList.data.length);
        } else if (snapshot.hasError) {
          return errorOnQuery;
        } else {
          return progressIndicator;
        }
      },
    );
  }

  Widget buildSubscriptionsList() {
    return FutureBuilder(
      future: widget.presenter.getSubscriptions(),
      builder:
          (BuildContext context, AsyncSnapshot<SubscriptionList> snapshot) {
        if (snapshot.hasData) {
          final subscriptionList = snapshot.data!;
          return ListView.separated(
              itemBuilder: (BuildContext context, int index) {
                final subscription = subscriptionList.data.elementAt(index);
                final id = subscription.id;
                final plan = subscription.planId;
                final userId = subscription.userId;
                final isAvailable = subscription.isActive;

                return ListTile(
                    title: Text(id.toString()),
                    leading: isAvailable
                        ? Icon(Icons.check, color: Colors.lightGreen)
                        : Icon(Icons.close, color: Colors.red),
                    trailing: Container(
                        width: 80,
                        child: Row(
                          children: [
                            IconButton(
                              icon:
                                  const Icon(Icons.disabled_by_default_rounded),
                              onPressed: () {
                                widget.presenter
                                    .disableSubscription(id)
                                    .then((_) => setState(() {}));
                              },
                            ),
                            IconButton(
                              icon: const Icon(Icons.delete_forever),
                              onPressed: () {
                                widget.presenter
                                    .deleteSubscription(id)
                                    .then((_) => setState(() {}));
                              },
                            ),
                          ],
                        )),
                    subtitle: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text('Subscription plan: $plan'),
                        Text('User id: $userId'),
                      ],
                    ));
              },
              separatorBuilder: (BuildContext context, int index) {
                return const Divider();
              },
              itemCount: subscriptionList.data.length);
        } else if (snapshot.hasError) {
          return errorOnQuery;
        } else {
          return progressIndicator;
        }
      },
    );
  }
}
