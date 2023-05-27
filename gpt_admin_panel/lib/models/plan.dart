class Plan {
  String id;
  String name;
  int monthlyPromptLimit;

  Plan(this.id, this.name, this.monthlyPromptLimit);

  factory Plan.fromJson(Map<String, dynamic> json) {
    return Plan(json['id'], json['name'], json['monthlyPromptLimit']);
  }
}

class PlanList {
  List<Plan> data;

  PlanList({required this.data});

  factory PlanList.fromJson(Map<String, dynamic> json) {
    List<dynamic> jsonData = json['data'];
    List<Plan> dataList = jsonData.map((json) => Plan.fromJson(json)).toList();
    return PlanList(data: dataList);
  }
}
