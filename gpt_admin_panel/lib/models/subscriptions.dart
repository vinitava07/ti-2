import 'package:gpt_admin_panel/models/plan.dart';
import 'package:uuid/uuid.dart';

class Subscription {
  String id;
  String userId;
  String planId;
  DateTime createdAt;
  DateTime expirationDate;
  bool isActive;

  Subscription(this.id, this.userId, this.planId, this.createdAt, this.isActive,
      this.expirationDate);

  factory Subscription.fromJson(Map<String, dynamic> json) {
    return Subscription(
        (json['id']),
        json['userID'],
        json['planID'],
        DateTime.fromMicrosecondsSinceEpoch((json['createdAt'] as int) * 1000),
        json['isActive'],
        DateTime.fromMicrosecondsSinceEpoch(
            (json['expirationDate'] as int) * 1000));
  }
}

class SubscriptionList {
  List<Subscription> data;
  SubscriptionList({required this.data});

  factory SubscriptionList.fromJson(Map<String, dynamic> json) {
    List<dynamic> jsonData = json['data'];
    List<Subscription> dataList =
        jsonData.map((json) => Subscription.fromJson(json)).toList();
    return SubscriptionList(data: dataList);
  }
}
