import 'package:flutter/material.dart';
import 'package:gpt_admin_panel/screens/info/info_presenter.dart';
import 'package:gpt_admin_panel/ui/components/organisms/edit_plan_popup.dart';

class PlanListTile extends StatelessWidget {
  final InfoPresenter presenter;
  final String name;
  final int monthLimit;
  final Function onReload;

  const PlanListTile(
      {Key? key,
      required this.presenter,
      required this.name,
      required this.monthLimit,
      required this.onReload})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(name),
      subtitle: Text('Monthly prompt limit: $monthLimit'),
      trailing: IconButton(
        icon: const Icon(Icons.edit),
        onPressed: () {
          showDialog(
            context: context,
            builder: (BuildContext context) => EditPlanPopup(
              planName: name,
              onTap: (newLimit) {
                presenter.setNewPlanLimit(name, newLimit).then((_) {
                  onReload();
                });
              },
            ),
          );
        },
      ),
    );
  }
}
